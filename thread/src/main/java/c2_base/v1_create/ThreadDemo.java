package c2_base.v1_create;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c2_base.v1_createthread
 * 不管是那种创建线程的方式，启动线程的方法都是调用 线程的 start()
 */
public class ThreadDemo {
    public static void main(String[] args) {

        /*继承thread类 启动线程*/
        System.out.println("---创建 线程1---");
        Thread1 t = new Thread1();
        System.out.println("---启动 线程1---");
        t.start();

        //通过执行结果知道，新线程的创建并未使原来的线程处于阻塞状态
        System.out.println("啦啦啦");

        /* 实现 runnable 接口 启动线程*/
        //1.创建 Runnable实现类的实例
        Thread2 t2 = new Thread2();
        //2.将实例作为参数创建thread
        Thread th = new Thread(t2);
        //3.启动线程
        th.start();

        /* 使用 匿名内部类创建线程 */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("使用匿名内部类创建线程...");
                System.out.println("匿名线程执行完毕");
            }
        }).start();

    }
}

/*
 * 线程创建方式一：继承Thread类，重写 run()
 */
class Thread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println("i:" + i);
        }
        System.out.println("线程1 执行完毕");
    }
}

/*
 * 线程创建方式二：实现 runnable接口，实现 run()
 */
class Thread2 implements Runnable{
    @Override
    public void run() {
        for (char i='A'; i <'H' ; i++) {
            System.out.println("i:" + i);
        }
        System.out.println("线程2 执行完毕");
    }
}
