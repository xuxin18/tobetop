package c3_advance.v1_communication;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * @author xuxin
 * @version v1.0
 * @project Inter-thread_Communication
 * @package communication.study
 * ExecutorService 的用法
 *      submit: 提交异步线程并返回执行结果（Future）
 *      execute:提交异步线程，不返回执行结果
 *
 */
public class Demo6 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("====进入主线程执行任务");

        //通过线程池管理多线程
        ExecutorService threadPool = Executors.newCachedThreadPool();

        //线程池提交一个异步任务
        System.out.println("====提交异步任务");
        //todo submit 后jdk 帮我们做了什么处理 待分析
        Future<HashMap<String,String>> future = threadPool.submit(new Callable<HashMap<String,String>>() {

            @Override
            public HashMap<String,String> call() throws Exception {

                System.out.println("异步任务开始执行....");
                Thread.sleep(10000);
                System.out.println("异步任务执行完毕，返回执行结果!!!!");

                return new HashMap<String,String>(){
                    {this.put("futureKey", "成功获取future异步任务结果");}
                };
            }

        });

        System.out.println("====提交异步任务之后，立马返回到主线程继续往下执行");
        Thread.sleep(1000);

        System.out.println("====此时需要获取上面异步任务的执行结果");

        boolean flag = true;
        while(flag){
            /*
                isDone():如果任务结束，无论
                                            正常结束
                                            中途取消
                                            发生异常  这些都返回 true
                isCancelled(): 如果任务完成前 被取消 则返回 true
                cancel(boolean mayInterruptRunning):
                        当任务未启动或者已结束，执行cancel() 返回false
                        当任务已启动且未结束：执行 cancel(true),如果中断成功返回 true
                                           执行 cancel(false),不对任务产生影响，返回false

             */
            //异步任务完成并且未被取消，则获取返回的结果
            if(future.isDone() && !future.isCancelled()){
                HashMap<String,String> futureResult = future.get();
                System.out.println("====异步任务返回的结果是："+futureResult.get("futureKey"));
                flag = false;
            }
        }

        //关闭线程池
        if(!threadPool.isShutdown()){
            threadPool.shutdown();
        }
    }
}
