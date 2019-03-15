package c3_advance.v2_ReenLock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c3_advance.v2_ReenLock
 *
 * condition(条件锁)的用法
 *      await() 基本等同于 Object 的 wait()
 *      signal()  基本等同于 Object 的 notify()
 *      signalAll  基本等同于 Object 的 notifyAll()
 *
 *      优点：可对对同一个锁对象（Lock）设置多个 条件锁（Condition对象）
 *
 *
 * 打印1到9这9个数字，由A线程先打印1，2，3，然后由B线程打印4,5,6，然后再由A线程打印7，8，9.
 */
public class ConditionDemo {
    private static AtomicInteger i = new AtomicInteger(1);
    public static void main(String[] args) {

        final Lock lock = new ReentrantLock();

        //条件：输出到3时
        Condition reachThree = lock.newCondition();
        //条件：输出到6时
        Condition reachSix = lock.newCondition();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("A线程开始打印");
                    while (i.get() < 4){
                        System.out.println(i.getAndIncrement());
                    }
                    //输出到3时通知B线程开始执行
                    reachThree.signal();
                    System.out.println("哈哈哈");
                }finally {
                    lock.unlock();
                }

                lock.lock();
                try {
                    try {
                        //等待输出6的条件
                        reachSix.await();
                        System.out.println("A线程被唤醒");
                        System.out.println("A线程开始打印");
                        while (i.get() < 10){
                            System.out.println(i.getAndIncrement());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (i.get() < 4){
                        reachThree.await();
                    }
                    System.out.println("B线程被唤醒");
                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

                lock.lock();
                try {
                    System.out.println("B线程开始打印");
                    while (i.get() <7){
                        System.out.println(i.getAndIncrement());
                    }
                    //唤醒 A线程
                    reachSix.signal();
                }finally {
                    lock.unlock();
                }

            }
        });

        threadA.start();
        threadB.start();

    }
}
