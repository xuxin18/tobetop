package c3_advance.v1_communication;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xuxin
 * @version v1.0
 * @project Inter-thread_Communication
 * @package communication.study
 * 3人参加万米长跑，等他们都准备好后，同时开始跑步
 */
public class Demo4 {
    public static void main(String[] args) {
        runABCWhenAllReady();
    }

    public static void runABCWhenAllReady(){
        int runner = 3;
        //设置同时等待的线程数
        CyclicBarrier cb = new CyclicBarrier(runner);

        final Random random = new Random();
        for (char runnerName='A'; runnerName<='C'; runnerName++){
            final String rn = String.valueOf(runnerName);
            new Thread(()->{
                long prepareTime = random.nextInt(10000) + 100;
                System.out.println("runner" + rn + "需要" + prepareTime + "ms的准备时间");
                try {
                    Thread.sleep(prepareTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("runner" + rn + "准备好了，就等其他跑步者准备了");
                try {
                    //每个线程调用 await()方法时，都会告诉 CyclicBarrier（循环栅栏）已到达栅栏（其实就是runner数-1）
                    // 然后将当前线程阻塞，放入一个 Lock的条件队列 中
                    //当所有线程都到达栅栏后，将所有的 Lock条件队列中的 线程放入到 Lock的等待队列中
                    // （条件锁调用signalAll() ,相当于notify所有thread）
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                //所有人都准备好了，开始跑步
                System.out.println("runner" + rn + "开始跑步");
            }).start();
        }
    }
}
