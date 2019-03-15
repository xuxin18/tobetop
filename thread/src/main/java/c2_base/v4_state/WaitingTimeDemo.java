package c2_base.v4_state;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c2_base.v4_state
 */
public class WaitingTimeDemo {

        //锁
        private static final Object lock=new Object();

        public static void main(String[] args) {
            //展示线程
            Thread showThread = new Thread(new WaitTask());
            System.out.println(showThread.getName() +"状态1"+ showThread.getState());
            showThread.start();
            System.out.println(showThread.getName() +"状态2"+showThread.getState());
            //循环读取展示线程状态，直到读到展示线程状态为WAITING，才让辅助线程唤醒等待线程。
            while (true){
                if(showThread.getState()==Thread.State.TIMED_WAITING){
                     System.out.println(showThread.getName() +"状态3"+ Thread.State.TIMED_WAITING);
                    break;
                }
            }

            try {
                showThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //线程执行完毕打印状态。
             System.out.println(showThread.getName() +"状态5"+ showThread.getState());
        }


        private static class WaitTask implements Runnable {
            @Override
            public void run() {
                //等待
                synchronized (lock){
                    try {
                        lock.wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"状态4"+Thread.currentThread().getState() + System.currentTimeMillis());
            }
        }
    
}
