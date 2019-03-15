package c3_advance.v2_ReenLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c3_advance.v2_ReenLock
 *
 *
 */
public class InterRuptDemo {
    public static void main(String[] args) throws InterruptedException {

        final Lock l = new ReentrantLock();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    l.lock();
                    while (true){
                        System.out.println("t1拿到了锁");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    l.unlock();
                }

            }
        }, "t1");

        t1.start();

        // main线程睡一秒钟，为了让t1线程完成启动
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("t2启动了");
                try {
                    /*
                        lockInterruptibly():
                            1.如果其他线程没有持有锁，则当前线程马上获得锁
                            2.如果其他线程持有锁，在当前线程未被中断的情况下，等待其他的线程执行完毕，再获取锁
                                               在当前线程被interrupt()中断的情况下，马上获取锁
                            3.如果该线程已经处于中断状态，或者在等待获取锁的同时被中断，则抛出 InterruptedException
                                同时清除中断状态
                     */
                    l.lockInterruptibly();
                    System.out.println("t2获得了锁");
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                    System.out.println("t2被中断等待");
                }

                try {
                    System.out.println("t2正在执行");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("t2异常");

                } finally {
                    l.unlock();
                }
            }
        }, "t2");

        t2.start();

        // main线程睡一秒钟，为了t2线程完成启动
        TimeUnit.SECONDS.sleep(1);

        /*
            当对一个线程，调用 interrupt() 时，
            ① 如果线程处于被阻塞状态（例如处于sleep, wait, join 等状态），那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。仅此而已。
            ② 如果线程处于正常活动状态，那么会将该线程的中断标志设置为 true，仅此而已。
                    被设置中断标志的线程将继续正常运行，不受影响。interrupt() 并不能真正的中断线程，需要被调用的线程自己进行配合才行。

            也就是说，一个线程如果有被中断的需求，那么就可以这样做。
                ① 在正常运行任务时，经常检查本线程的中断标志位，如果被设置了中断标志就自行停止线程。
                ② 在调用阻塞方法时正确处理InterruptedException异常。
        */
        t2.interrupt();

    }

}
