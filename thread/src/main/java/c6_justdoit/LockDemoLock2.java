package c6_justdoit;

import java.util.concurrent.locks.Lock;

/**
 * @author xuxin
 * @version v1.0
 * @project study
 * @package PACKAGE_NAME
 * @date 22 16:${MIMUTE}
 * @modified
 */
public class LockDemoLock2 {

    volatile int i = 0;

    //Lock lock = new ReentrantLock();
    Lock lock = new MyLock();
    public void add(){
        lock.lock();
        try {
            i++;
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i< 100; i++){
            System.out.print("i=" + i + "  ");
            test();
        }
    }

    public static void test() throws InterruptedException{
        LockDemo1 ld = new LockDemo1();

        for (int i=0; i<2; i++){
            new Thread(() -> {
                for (int j=0; j<10000; j++){
                    ld.add();
                }
            }).start();
        }
        Thread.sleep(50);
        System.out.println(ld.i);
    }
}
