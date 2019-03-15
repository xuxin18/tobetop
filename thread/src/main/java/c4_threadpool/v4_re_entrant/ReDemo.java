package c4_threadpool.v4_re_entrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c4_threadpool.v4_re_entrant
 *
 * 证明 ReentrantLock 是可重入锁
 */
public class ReDemo {
    public static void main(String[] args) {
        Test s = new Test();
        new Thread(s).start();
        new Thread(s).start();
        new Thread(s).start();

    }

    static class Test implements Runnable{
        ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            get();
        }

        public void get(){
            lock.lock();
            System.out.println(Thread.currentThread().getId());
            set();
            lock.unlock();
        }

        public void set(){
            lock.lock();
            System.out.println(Thread.currentThread().getId());
            lock.unlock();
        }
    }
}
