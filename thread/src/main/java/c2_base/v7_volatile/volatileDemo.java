package c2_base.v7_volatile;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c2_base.v8_volatile
 *
 * 被volatile修饰的变量的特性：
 *                  保证了此变量对所有线程的可见性（有一个线程修改了变量的值，其他线程能够立刻得到这个修改的值），但不能保证变量的原子性
 *                  禁止指令重排序优化
 *
 */
public class volatileDemo {
    public static volatile int race = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i]= new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });

            threads[i].start();
        }

        //确保创建的20个线程运行完毕后再运行主线程
       /* for (int i = 0; i < 20; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        //activeCount() 当期程序中的存活的线程数（至少有两个：一个主线程，一个系统线程）
        //确保创建的20个线程运行完毕
        while (Thread.activeCount() > 2){
            // yield() 让当前线程释放cpu资源（即：让当前线程停止执行，让其他线程执行）。也有人把这种状态叫做 线程谦让
            Thread.yield();
        }
        //isAlive() 检测线程是否存活 （happens-before：线程启动原则）
        System.out.println(threads[5].isAlive());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();


        //发现每次打印结果都不一致，这说明当 多个线程 对同一个变量进行写操作时，基于volatile的变量的运算在并发下不是安全的
        System.out.println(race);

    }

    public static void increase(){
        race++;
    }

}
