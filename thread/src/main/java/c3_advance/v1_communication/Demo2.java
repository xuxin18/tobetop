package c3_advance.v1_communication;

/**
 * @author xuxin
 * @version v1.0
 * @project Inter-thread_Communication
 * @package communication.study
 *
 * 如何以指定的方式有序的始两个线程交叉运行
 *
 * 注意： wait()、notify()、notifyAll() 只能在 synchronized代码块中使用
 *                                     只能对进入同一监视器（获得过同一把锁）的线程起作用
 */
public class Demo2 {
    public static void main(String[] args) {
        method1();
    }

    //B线程在A线程打印 1 后，开始打印1、2、3；A线程继续打印2,3
    public static void method1(){
        Thread A = new Thread(()->{
            synchronized (Demo2.class){
                System.out.println("线程A 打印：1");
                try {
                    /*
                      wait() 与 sleep() 的区别：
                            sleep 是Thread 类的方法，不释放锁资源；一段时间后，继续执行
                            wait  是Object 类的方法，释放锁资源； 等待notify()或者 notifyAll() 唤醒
                                                      注意：锁A调用wait()只能由锁A调用notify()或notifyAll()唤醒
                                                                          其他锁调用notify()等方法不能唤醒锁A的wait()


                      让当前线程 等待，并释放锁资源
                     */
                    Demo2.class.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("线程A 打印：2");
            System.out.println("线程A 打印：3");
        });

        Thread B = new Thread(()->{
            synchronized (Demo2.class){
                System.out.println("线程B 打印：1");
                System.out.println("线程B 打印：2");
                System.out.println("线程B 打印：3");
                //
                Demo2.class.notify();

                /* 发现执行 notify()后，这段代码也会执行。
                   这说明 程序执行到notify()后并没有马上释放锁资源，只是通知 A线程可以开始争抢锁资源
                   等到 当前同步代码块内的代码执行完毕后，再释放锁资源

                   notify() : 随机通知一个 处于阻塞中的线程可以开始争抢锁资源
                   notifyAll():通知所有处于阻塞中的线程可以争抢锁资源

                */

                int i = 0;
                while (i++ < 500){
                    System.out.println("线程B 打印：4");
                }

            }
        });

        A.start();
        B.start();
    }
}
