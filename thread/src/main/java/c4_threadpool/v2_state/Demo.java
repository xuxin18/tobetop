package c4_threadpool.v2_state;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c4_threadpool.v2_state
 *
 * 线程池的生命周期
 *      RUNNING：线程池初始化状态（线程池一旦被创建，就处于RUNNING状态）
 *                  RUNNING状态能够接收新任务，以及对已添加的任务进行处理
 *      SHUTDOWN：线程池调用shutdown()方法时，状态由 RUNNING -> SHUTDOWN
 *                  SHUTDOWN状态不能就收新任务，但能处理已添加的任务
 *      STOP：线程池调用 shutdownNow() 方法时，线程池由 (RUNNING OR SHUTDOWN)-> STOP
 *              STOP状态不接收新任务，不处理已添加的任务，并且中断正在处理的任务
 *      TIDYING：当所有任务已终止后，线程池状态会改变为TIDYING状态，当线程池变为TIDYING状态时，会执行钩子函数terminated()
 *               如果用户想在线程池变为TIDYING时，进行相应处理，可以通过重载ternimated()函数来实现
 *      TERMINATED：线程池彻底终止
 *
 */
public class Demo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        System.out.println("延迟3秒输出");
        for (int i = 0; i < 1000000000; i++) {
            final int temp = i;
            scheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("i:" + temp);
                }
            }, 3, TimeUnit.SECONDS);
        }
    }

}
