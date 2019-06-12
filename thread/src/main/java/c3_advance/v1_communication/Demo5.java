package c3_advance.v1_communication;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xuxin
 * @version v1.0
 * @project Inter-thread_Communication
 * @package communication.study
 * 实际开发中，我们需要创建子线程去做一些耗时的任务，然后将执行结果反馈给主线程
 * Runnable中 run()  返回的数据类型为 void
 * Callable中 call() 返回的数据类型为 泛型
 */
public class Demo5 {
    public static void main(String[] args) {
        System.out.println("#####");
        doTaskWithResultInWorker();
        System.out.println("#####");
    }

    //子线程计算 1到100的和
    private static void  doTaskWithResultInWorker(){
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("子线程开始工作");
                Thread.sleep(1000);
                int result = 0;
                for (int i=0; i<101; i++){
                    result += i;
                }
                System.out.println("返回子线程执行结果");
                return result;
            }
        };

        // 将 Callable接口的 实例 作为 构造参数 来创建 futureTask
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);

        //todo 此处实际是 采用了 适配器模式 将 FutureTask适配成Runnable 等学好适配器模式后再来分析
        new Thread(futureTask).start();


        try {
            System.out.println("在 futureTask.get() 执行之前");
            // futureTask.get() 方法会阻塞主线程，然后 Callable 开始调用 call()
            //call() 执行完毕后，将结果返回；然后futureTask.get() 获取结果，主线程恢复运行
            System.out.println("执行 futureTask.get() 的结果：" + futureTask.get());
            System.out.println("在 futureTask.get() 执行之后");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}


