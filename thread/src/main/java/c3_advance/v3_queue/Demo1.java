package c3_advance.v3_queue;

import java.util.ArrayDeque;
import java.util.concurrent.*;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c3_advance.v3_queue
 *
 * jdk提供了两套队列，分别是：
 *      ConcurrentLinkedQueue 为代表的 高性能队列(因为 ConcurrentLinkedQueue 是非阻塞队列，底层原理利用CAS实现)
 *      BlockingQueue 为代表的阻塞队列
 *
 * Queue的3组方法：
 *                     抛出异常    返回特殊值
 *      插入（尾部）：    add(e)    offer(e)
 *      移除（头部）：   remove()   poll()
 *      检查（头部）：   element()  peek()
 *
 * Queue 通常不允许插入null，尽管某些实现（例如：LinkedList）是允许的，但是也不建议
 *
 * FIFO：先进先出
 * LIFO：后进先出（这种队列用于优先处理最近发生的事情）
 *
 * BlockingQueue：阻塞队列，为了解决多线程中数据高效安全传输而提出的
 *
 * 多线程中很多场景都能用队列实现。比如经典的 生产者/消费者模型，通过队列可以方便的实现两者之间的共享
 *      定义一个生产者线程、一个消费者线程，通过阻塞队列共享数据就可以了。
 *
 * BlockingQueue特有的方法：
 *      put(e):将元素e添加到BlockingQueue中，如果队列没有空间存放，则调用线程阻塞，进入等待状态，直到BlockingQueue
 *              有空间，再继续插入
 *      take():取走BlockingQueue中的头部元素，如果队列为空，则调用线程被阻塞，进入等待，直到BlockingQueue中有新数据
 *              加入
 *      drainTo(Collection<? super E> c, int m): 一次性取走m个BlockingQueue中的数据到集合c中。该方法可以提升获取
 *                                                  数据的效率（不需要多次分批加锁或者释放锁）
 *
 */
public class Demo1 {
    public static void main(String[] args) {
        /*
            ArrayBlockingQueue: 基于数组实现的阻塞队列，必须指定队列大小。
            由于 ArrayBlockingQueue中只有一个ReentrantLock对象，这意味着 生产者和消费着无法并行运行

            参数fair： 为true代表ArrayBlockingQueue中的锁是公平锁
                      为false或者不传，则为 非公平锁
         */
        ArrayBlockingQueue<String> queue1 = new ArrayBlockingQueue<>(3,true);

        /*
            LinkedBlockingQueue:基于链表实现的阻塞队列，如果不指定队列大小，则默认容量最大为 Integer.MAX_VALUE
            生产者和消费者都有自己的锁，所以生产者和消费者可以同时运行
         */
        LinkedBlockingQueue<Object> queue2 = new LinkedBlockingQueue<>();

        /*
            SynchronousQueue:内部仅仅允许容纳一个元素，当线程插入一个元素后，会被阻塞，除非这个元素被另一个线程消费
             参数fair： 为true代表SynchronousQueue中的锁是公平锁
                      为false或者不传，则为 非公平锁
         */
        SynchronousQueue<Object> queue3 = new SynchronousQueue<>(true);

        /*
            PriorityBlockingQueue:优先级阻塞队列，它的默认容量为11，最大容量为 Integer.MAX_VALUE-8
            所有插入 PriorityBlockingQueue的对象必须 实现 Comparable接口，队列优先级的排序规则按照对象中的
            实现的compareTo方法来 排序
         */
        PriorityBlockingQueue<Object> queue4 = new PriorityBlockingQueue<>();

        /*
         todo 实现原理：https://infoq.cn/article/ConcurrentLinkedQueue
         非阻塞队列（基于CAS实现）
         */
        ConcurrentLinkedQueue queue5 = new ConcurrentLinkedQueue();

        /*
            DelayQueue: 延时队列
            todo 实现原理 使用场景
            https://blog.csdn.net/guangcigeyun/article/details/8278355
            https://blog.csdn.net/superdog007/article/details/53944884
            https://my.oschina.net/lujianing/blog/705894
         */
        DelayQueue delayQueue = new DelayQueue();

        /*
            基于数组实现的 双向队列
         */
        ArrayDeque dq1 = new ArrayDeque(10);

        /*
            基于链表实现的双向阻塞队列
         */
        LinkedBlockingDeque dq2 = new LinkedBlockingDeque();

    }
}
