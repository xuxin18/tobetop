package c2_base.v2_method;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c2_base.v2_method
 * 线程中常用方法
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {

        //指定创建的线程的名字
        Thread t1 = new Thread("t线程");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        }, "r线程");

        //获取指定线程的的名称
        System.out.println(t1.getName());
        System.out.println(t2.getName());

        //获取指定线程的ID，线程编号从0开始
        System.out.println(t1.getId());
        System.out.println(t2.getId());

        //获取执行当前代码块的线程
        System.out.println(Thread.currentThread());

        //让执行当前代码的线程休眠 （不释放锁资源）
        System.out.println(Thread.currentThread() + "开始休眠100ms，当前时间为：" + System.currentTimeMillis());
        Thread.sleep(100);
        System.out.println(Thread.currentThread() + "休眠完毕     ，当前时间为：" + System.currentTimeMillis());


        //启动指定线程
        t2.start();

        //查看指定线程的状态
        t2.getState();
        System.out.println( t2.getState());

    }
}
