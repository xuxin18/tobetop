package c4_threadpool.v1_create;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c4_threadpool.v1_create
 *
 * Executor框架的最顶层实现是： ThreadPoolExecutor
 *
 * 创建ThreadPoolExecutor时比较重要的参数：
 *      corePoolSize: 核心池大小。当有任务来之后，就会创建一个线程去执行任务，当线程池中
 *                      的线程数目达到 corePoolSize后，就会把到达的任务放到 任务队列 中
 *      maximumPoolSize：线程池最大线程数。当线程池中的线程数 大于或等于 corePoolSize，且
 *                          任务队列已满时，线程池会创建新的线程，直到数量达到 maxPoolSize.
 *                          如果线程数等于 maxPoolSize，且任务队列已满，则已超出线程池的处理能力
 *                          线程池会拒绝处理任务而抛出异常
 *      keepAliveTime：当线程不执行任务时，最多能保持存活的时间。线程数量只增加不减少也不行。当负载降低时，可减少线程数量，如果一个线程空闲时间达到keepAliveTiime，
 *                      该线程就退出。默认情况下线程池最少会保持corePoolSize个线程。
 *      unit：时间单位
 *      workQueue：任务队列（是一个阻塞队列BlockingQueue），注意这个任务队列需要明确指定大小
 *      rejectedExecutionHandler：拒绝策略。当线程池和任务队列都满了后对 创建新线程的请求的处理方式
 *                                      DiscardPolicy ： 直接丢弃
 *                                      DiscardOldestPolicy：丢弃队列中最老的任务
 *                                      AbortPolicy：抛异常
 *                                      CallerRunsPolicy：将任务分给调用线程来执行（即不用线程池中的线程来运行，如果任务请求是位于线程M中，则该任务交给线程M执行
 *                                          例如：位于Main线程的任务x请求由线程池中的线程来运行，当线程池任务已满时，任务x则由Main线程执行）
 *
 *
 * todo  线程池的四种拒绝策略 https://blog.csdn.net/u010412719/article/details/52132613
 *
 */
public class ExecutorDemo {

    private static SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {

        System.out.println("jvm可用的处理器数量为：" + Runtime.getRuntime().availableProcessors());
        int corePoolSize = 1;
        int maximumPoolSize = 1;
        BlockingQueue queue = new ArrayBlockingQueue<Runnable>(1);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,  maximumPoolSize,
                0, TimeUnit.SECONDS, queue ) ;
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i=0;i<10;i++){
            final int index = i;
            pool.submit(new Runnable(){

                @Override
                public void run() {
                    log(Thread.currentThread().getName()+" begin run task :"+index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log(Thread.currentThread().getName()+" finish run  task :"+index);
                }

            });
        }

        log("main thread before sleep!!!");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("before shutdown()");

        pool.shutdown();

        log("after shutdown(),pool.isTerminated=" + pool.isTerminated());
        try {
            pool.awaitTermination(1000L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("now,pool.isTerminated=" + pool.isTerminated());
    }

    protected static void log(String string) {
        System.out.println(sdf.format(new Date())+"  "+string);
    }

}
