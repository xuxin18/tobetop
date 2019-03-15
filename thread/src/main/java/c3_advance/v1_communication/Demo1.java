package c3_advance.v1_communication;

/**
 * @author xuxin
 * @version v1.0
 * @project Inter-thread_Communication
 * @package communication.study
 * 如何让两个线程按顺序执行？
 *
 * eg.有两个线程：线程A，线程B。
 */
public class Demo1 {
    public static void main(String[] args) {
        //method1();
        method2();

    }

    //两个线程都可以按顺序打印数字1、2、3
    private static void method1(){
        // lamda语法，相当于下面 Thread B 的写法
        Thread A = new Thread(()->{
            printNum("线程A");
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                printNum("线程B");
            }
        });

        A.start();
        B.start();
    }

    //B线程 在 A线程打印完毕后再开始打印  thread.join() 方法:等待调用join方法的线程执行完毕后，再执行当前线程
    private static void method2(){
        // lamda语法，相当于下面 Thread B 的写法
        Thread A = new Thread(()->{
            printNum("线程A");
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("需要在线程A执行结束后再执行B");
                try {
                    A.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printNum("线程B");
            }
        });

        A.start();
        B.start();
    }

    private static void printNum(String threadName){
        int i = 0;
        while (i++ < 9){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " 打印：" + i);
        }
    }

}
