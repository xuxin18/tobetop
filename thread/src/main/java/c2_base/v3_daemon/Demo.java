package c2_base.v3_daemon;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c2_base.v3_daemon
 * 守护线程：当主线程挂了，守护线程也会被自动销毁
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Thread daemon = new Thread(()->{
            int i = 0;
           while (true){
               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("守护线程在运行" + i++);
           }
        });
        //将创建的线程设置为守护线程
        daemon.setDaemon(true);
        System.out.println("1" + daemon.getState());
        daemon.start();
        System.out.println("2" + daemon.getState());

        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            System.out.println("主线程在运行" + i);
        }
        System.out.println("主线程执行完毕");
        System.out.println("3" + daemon.getState());


    }
}
