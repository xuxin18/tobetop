package c4_threadpool.v5_spin;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c4_threadpool.v5_spin
 * @date 13 10:${MIMUTE}
 * @modified 这个代码执行会导致 cpu占用率100%，轻易不要执行
 */
public class TestSpinLock {

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new MySpin(spinLock));
            t.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(MySpin.sum);

    }

    static class MySpin implements Runnable{

        static int sum;

        private SpinLock spinLock;

        public MySpin(SpinLock spinLock) {
            this.spinLock = spinLock;
        }

        @Override
        public void run() {
            this.spinLock.lock();
            this.spinLock.lock();
            sum++;
            this.spinLock.unlock();
            this.spinLock.unlock();
        }
    }
}
