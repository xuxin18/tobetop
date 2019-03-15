package c3_advance.v1_communication;

import java.util.concurrent.CountDownLatch;

/**
 * @author xuxin
 * @version v1.0
 * @project Inter-thread_Communication
 * @package communication.study
 * A、B、C 线程可以同时运行。当它们都运行完毕后，D线程才开始运行。
 * CountDownLatch 适用于 一个线程需要等待多个线程的情况
 */
public class Demo3 {

    public static void main(String[] args) {
        runDAfterABC();
    }

    private static void runDAfterABC(){
        int priority_thread_count = 3;
        CountDownLatch cdl = new CountDownLatch(priority_thread_count);
        new Thread(()->{
            System.out.println("线程D在等起其他线程执行...");

            try {
                //检查计数器值是否为0，如果值不为0，将阻塞.为0则触发当前线程继续执行
                cdl.await();
                System.out.println("其他线程都执行完毕了，线程D开始执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (char threadName='A'; threadName <='C'; threadName++){
            final String tn = String.valueOf(threadName);
            new Thread(()->{
                System.out.println("线程" + tn + " 正在运行");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + tn + " 执行完毕");
                //将计数器的值 -1
                cdl.countDown();
            }).start();
        }

    }

}
