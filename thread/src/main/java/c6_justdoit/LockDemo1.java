package c6_justdoit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuxin
 * @version v1.0
 * @project study
 * @package PACKAGE_NAME
 * @date 22 16:${MIMUTE}
 * @modified
 */
public class LockDemo1 {

    //volatile int i = 0;
    AtomicInteger i = new AtomicInteger(0);

    public void add(){
        //i++;
        i.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i< 100; i++){
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
