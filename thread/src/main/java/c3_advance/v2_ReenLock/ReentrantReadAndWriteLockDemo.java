package c3_advance.v2_ReenLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c3_advance.v2_ReenLock
 *
 */
public class ReentrantReadAndWriteLockDemo {
    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    public static void main(String[] args) {

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            threads[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    get("啦啦啦");
                    //put(temp+"",temp + "倍快乐");
                }
            }, i+"");
            threads[i].start();

        }

    }

    public static final Object get(String key){
        r.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读操作，key：" + key + " 开始");
            Thread.sleep(1000);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读操作，key：" + key + " 结束");
            return o;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            r.unlock();
        }
        //当出现异常的时候，将传入的参数返回给调用者
        return key;
    }

    public static final Object put(String key, Object value){
        w.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写操作，key：" + key + ",value: " + value + " 开始");
            Thread.sleep(1000);
            Object o = map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "写操作，key：" + key + ",value: " + value + " 结束");
            return o;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.unlock();
        }
        return key;
    }

}
