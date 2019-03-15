package c6_justdoit;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xuxin
 * @version v1.0
 * @project study
 * @package PACKAGE_NAME
 * @date 22 16:${MIMUTE}
 * @modified
 */
public class LockDemoCAS {

    volatile int value = 0;

    static Unsafe unsafe;
    static  long valueOffset;

    static {
        //通过反射来获取unsafe对象
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            //通过字段来获取字段所在的实例对象
            //当字段是 静态字段的时候传入任何对象都可以，包括 null
            //当字段不是 静态字段时，需要传入反射类的对象。例如：假设需要反射的类是 c6_justdoit.LockDemo，则写法为  field.get(new c6_justdoit.LockDemo())
            unsafe = (Unsafe) field.get(null);

            //获得需要修改的value的偏移量
            valueOffset = unsafe.objectFieldOffset(LockDemoCAS.class.getDeclaredField("value"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void add(){
        int current;
        //通过CAS来修改 value
        do {
            current = unsafe.getIntVolatile(this, valueOffset);
        }while (!unsafe.compareAndSwapInt(this, valueOffset, current, current+1));

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i< 100; i++){
            test();
        }
    }

    public static void test() throws InterruptedException{
        LockDemoCAS ld = new LockDemoCAS();

        for (int i=0; i<2; i++){
            new Thread(() -> {
                for (int j=0; j<10000; j++){
                    ld.add();
                }
            }).start();
        }
        Thread.sleep(50);
        System.out.println(ld.value);
    }
}
