package c3_advance.v3_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c3_advance.v3_queue
 * 使用 BlockingQueue来模拟生产者与消费者
 *
 * 阻塞队列不用担心 队列空或是满后导致数据错乱的问题，因为阻塞队列都有两把锁：
 *      notEmpty：队列已满，等待 take()执行
 *      notFull：队列为空，等待 put()执行
 */
public class ProducerCustomer {

    static final BlockingQueue  blockingQueue = new ArrayBlockingQueue(10,true);

    public static void main(String[] args) {

        Thread producerThread = new Thread(new Runnable() {
            int i = 1;
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(100);
                        blockingQueue.put(i + " ");
                        System.out.println("张三  生产  了一个" + i++);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread customerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(300);
                        System.out.println("李四  消费  了一个" + blockingQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        producerThread.start();
        customerThread.start();
    }

}
