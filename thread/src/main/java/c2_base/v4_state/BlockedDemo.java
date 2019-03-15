package c2_base.v4_state;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c2_base.v4_state
 */
public class BlockedDemo {

    //锁
    private static final Object lock = new Object();
    //锁定标志
    private volatile static boolean lockFlag = true;
    //执行顺序
    private volatile static int order = 0;

    public static void main(String[] args) {
        //展示线程
        Thread showThread = new Thread(new Task(),"展示线程");
        System.out.println(showThread.getName() + "状态1" + showThread.getState());
        showThread.start();
        System.out.println(showThread.getName()  + "状态2" + showThread.getState());

        //辅助线程，制造 争抢锁资源的场景
        Thread assistantThread = new Thread(new SynTask(), "辅助线程");
        assistantThread.start();

        //循环读取展示线程状态，直到读到展示线程状态为 BLOCKED,才让辅助线程退出同步(释放锁资源)
        while (true){
            if (showThread.getState() == Thread.State.BLOCKED){
                System.out.println(showThread.getName() + "状态3"+ Thread.State.BLOCKED);
                lockFlag = false;
                break;
            }
        }

        try {
            assistantThread.join();
            showThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //线程执行完毕，打印状态
        System.out.println(showThread.getName()  + "状态5"+ showThread.getState());
    }

    private static class SynTask implements Runnable{
        @Override
        public void run() {
            while (true){
                //保证先进入同步范围
                if (order == 0){
                    synchronized (lock){
                        //启动另一个同步
                        order = 1;
                        //等待主线程读取到线程阻塞状态，退出同步
                        while (lockFlag){
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    private static class Task implements Runnable{
        @Override
        public void run() {
            while (true){
                //保证后进入同步范围
                if (order == 1){
                    synchronized (lock){
                        System.out.println(Thread.currentThread().getName()  + "状态4" + Thread.currentThread().getState());
                    }
                    break;
                }
            }
        }
    }


}
