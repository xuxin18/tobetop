package c2_base.v6_threadlocal;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c2_base.v7_threadlocal
 *
 * ThreadLocal的作用：为每一个使用该变量的线程提供一个独立的变量副本，并且不会对其他线程的副本造成影响
 * jdk建议将 ThreadLocal 修饰符设置为 private static，据说是为了防止内存泄露
 * 方法：
 *         set(Object value)：设置当前线程所对应的局部变量的值
 *         get()：获取当前线程所对应的局部变量的值
 *         void remove():将当前线程的局部变量删除（注意：当线程结束后，对应该线程的局部变量将自动被垃圾回收，
 *                                              所以显示调用remove()并不是必须的操作）
 *         initialValue()
 *
 * ThreadLocal的实现：
 *      jdk1.3之前：每个ThreadLocal类创建一个Map，然后使用 map.put(线程id,实例对象)
 *      jdk1.8中：  每个线程中维护一个 ThreadLocalMap 映射表，映射表的key是ThreadLocal实例本身
 *                  value是真正需要存储的 Object
 */
public class ThreadLocalDemo {

    //todo ThradLocal 在 servlet中用到，这个地方待研究
    private static final ThreadLocal<Integer> m = new ThreadLocal<Integer>(){
        //initialValue()设置 变量的初始值，该方法仅在调用 get() 或 set() 时才执行，且只执行一次
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread(i)).start();
        }
    }

    private static class MyThread implements Runnable{
        private int index;

        public MyThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println("线程" + index + "的初始value是：" + m.get());
            for (int i = 0; i < 10; i++) {
                m.set(m.get() + i);
            }
            System.out.println("线程" + index + "的累加value是：" + m.get());
        }
    }
}
