package c4_threadpool.v4_re_entrant;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c4_threadpool.v4_re_entrant
 *
 * 可重入锁的最大作用就是避免死锁
 *
 *
 * 证明 Synchronized 是可重入锁
 */
public class SyncDemo {
    public static void main(String[] args) {
        Test t = new Test();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }

    static class Test implements Runnable{
        @Override
        public void run() {
            get();
        }

        public synchronized void get(){
            System.out.println(Thread.currentThread().getId());
            set();
        }
        public synchronized void set(){
            System.out.println(Thread.currentThread().getId());
        }

    }
}
