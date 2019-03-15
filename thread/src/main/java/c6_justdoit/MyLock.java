package c6_justdoit;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xuxin
 * @version v1.0
 * @project study
 * @package PACKAGE_NAME
 *
 * 自定义一个 ReentrantLock
 */
public class MyLock implements Lock {
    //定义一个标志 - 使用AtomicReference保存当前获取到锁的线程，保证了compareAndSet操作的原子性
    AtomicReference<Thread> owner = new AtomicReference<>();

    //声明一个集合用来存储正在等待的线程
    public LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();


    @Override
    public void lock() {
        //cas 当执行lock()的线程没拿到锁时
        //owner 什么时候被赋值呢？ 整个系统第一次执行lock（）时，owner被赋值为Thread.currentThread()
        while (!owner.compareAndSet(null,Thread.currentThread())){
            /*
                执行lock（）的线程没拿到锁，需要等待，其他线程释放锁
             */
            waiters.add(Thread.currentThread());//将执行lock（）的线程加入到等待队列中
            LockSupport.park();//让执行lock（）的线程 处于等待状态，这是个阻塞的方法

            //当执行Lock（）的线程 接收到可以抢夺锁的通知(47行代码)后，需要将该线程 从 队列中移出，让它去争抢锁
            waiters.remove(Thread.currentThread());
        }
        //拿到锁之后执行接下来的代码
    }


    @Override
    public void unlock() {
        if (owner.compareAndSet(Thread.currentThread(), null)){
            //释放锁之后，通知其他所有线程，你们可以继续抢夺锁
            for (Thread t : waiters){
                LockSupport.unpark(t);//通知其他线程可以抢夺锁了
            }

        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
