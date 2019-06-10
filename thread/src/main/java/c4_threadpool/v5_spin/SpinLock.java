package c4_threadpool.v5_spin;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c4_threadpool.v5_spin
 *
 * 自定义一个自旋锁
 * 当一个线程 调用这个不可重入的自旋锁去加锁的时候没问题，当再次调用lock()的时候，因为自旋锁的持有引用已经不为空了，该线程对象会误认为是别人的线程持有了自旋锁
 * 由于自旋锁只是将当前线程不停地执行循环体，不进行线程状态的改变，所以响应速度更快。但当线程数不停增加时，性能下降明显，因为每个线程都需要执行，占用CPU
 *      时间。如果线程竞争不激烈，并且保持锁的时间段。适合使用自旋锁。
 *
 * todo 自旋锁不明白  AtomicReference 也不明白
 */
public class SpinLock {

    //定义一个标志-使用 AtomicReference 保存当前获取到锁的线程，保证了 compareAndSet操作的原子性
    private AtomicReference<Thread> owner = new AtomicReference<>();

    public void lock(){
        Thread current = Thread.currentThread();
        while (!owner.compareAndSet(null, current)){

        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        owner.compareAndSet(current,null);
    }
}
