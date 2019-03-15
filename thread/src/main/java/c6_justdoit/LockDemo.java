package c6_justdoit;

/**
 * @author xuxin
 * @version v1.0
 * @project study
 * @package PACKAGE_NAME
 * @date 22 16:${MIMUTE}
 * @modified
 */
public class LockDemo {

    volatile int i = 0;

    public void add(){
        i++;
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
