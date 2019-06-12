package c2_base.v5_safe;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c2_base.v6_safe
 * 150张火车票，3个窗口同时抢票，使用多线程模拟抢票效果
 *
 * SaleTicket_v1 中会出现 1号窗口和2号窗口卖了同一张票的情况
 * 解决方式一：内置锁 Synchronized
 * 解决方式二：显示锁 ReentrantLock
 */
public class SaleTicket_v2 {
    private static int tickets = 150;

    public static void main(String[] args) {
        SaleThread r = new SaleThread();
        Thread t1 = new Thread(r, "A号窗口");
        Thread t2 = new Thread(r, "B号窗口");
        Thread t3 = new Thread(r, "C号窗口");
        t1.start();
        t2.start();
        t3.start();

    }

    private static class SaleThread implements Runnable{
        @Override
        public void run() {
            while (tickets > 0){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sale();
            }
        }

        private void sale(){
            synchronized (this){
                if (tickets > 0){
                    System.out.println(Thread.currentThread().getName() + ",出售第" + (151-tickets) + "张票");
                }
                tickets--;
            }

        }
    }


}


