package c7_deadlock;

/**
 * @author xuxin
 * @project to_be_top
 * @package c7_deadlock
 * @date 2019/6/19 10:26
 * @description 死锁检测
 * 如果 程序是在 本地运行的，可以通过 jdk 的自带工具 jconsole.exe 或者 jvisualvm.exe 工具来检测。
 *      1.打开jconsole工具，选择对应的程序的端口连接，选择线程界面，点击死锁检测
 *      2.打开jvisualvm工具，选择对应的程序的端口连接，选择线程界面，如果当前程序出现死锁，该界面会提示检测到死锁
 *          然后点击线程dump 即可（dump文件的末尾会有具体的死锁信息）
 * 如果程序是在 linux 环境下运行，则可以通过以下命令查看：
 *      ps -aux | grep java
 *          查找程序对应的进程端口号（如果严重情况下可以使用top命令查看当前系统cpu/内存使用率最高的进行pid）
 *          ps 显示瞬间 process（进程） 的动态
 *              -aux 显示所有包含其他使用者的行程
 *      top -Hp 进程端口号
 *          查看进程中占用最多资源的线程的线程端口号（显示的是10进制的，要将它计算一下转为 16进制（printf "%x\n" 线程端口号））
 *          top 实时显示系统中各个进程的资源占用状态（类似windows的任务管理器）
 *      jstack 端口号 | grep -20 转换为16进制的线程端口号
 *          查询线程死锁信息
 *          jstack 生成jvm当前时刻线程快照
 *          -20 显示倒数20行数据
 *
 *  ps:
 *      将这个java文件直接上传到 linux上后，使用 javac DemoDead.java 发现编译成功，但是 使用 java DemoDead 运行时
 *          报错：找不到或无法加载主类。
 *      原因：这个 java 文件上有 包相关的 语句 package c7_deadlock;解决方式：
 *          带包编译：
 *              javac -d . DemoDead.java （这个地方其实就是在当前目录下创建了 c7_deadlock 文件夹，并将 DemoDead.class 放在了该文件夹下）
 *                  （发现： 直接 javac DemoDead.java，然后手动在当前目录下创建 c7_deadlock 文件夹 并将 DemoDead.class 剪切到  c7_deadlock 文件夹
 *                   下，最后在DemoDead.java所在目录下执行 下面的语句也能执行成功）
 *          运行：
 *              java c7_deadlock.DemoDead 或者 java c7_deadlock/DemoDead
 *          猜测：在使用 java DemoDead 命令执行类文件时，
 *                  1.先查看当前目录下能否找到 DemoDead.class文件，找不到则报错
 *                  2.找到则先扫描类文件中是否有包相关语句
 *                      有，则检查 之前找到的 DemoDead.class 是否在 包下；
 *                          不在，则报错
 *                          在，则执行成功
 *                      没有，则执行 DemoDead.class 文件成功
 *
 */
public class DemoDead implements Runnable{

    private String a = "";
    private String b = "";

    public DemoDead(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        new Thread(new DemoDead("A", "B"), "Thread-A").start();
        new Thread(new DemoDead("B", "A"), "Thread-B").start();
    }

    @Override
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "开始执行");
            synchronized (this.a) {
                System.out.println(threadName + " lock " + this.a);
                Thread.sleep(3 * 1000);
                synchronized (this.b) {
                    System.out.println(threadName + " lock " + this.b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
