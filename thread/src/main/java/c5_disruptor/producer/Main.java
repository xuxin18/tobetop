package c5_disruptor.producer;

import c5_disruptor.consumer.LongEventHandler;
import c5_disruptor.entity.LongEvent;
import c5_disruptor.factory.LongEventFactory;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c5_disruptor
 * todo https://tech.meituan.com/2016/11/18/disruptor.html
 */
public class Main {

    public static void main(String[] args) {
        //1.创建一个可缓存的线程 提供线程来触发 consumer 的事件处理
        ExecutorService executor = Executors.newCachedThreadPool();

        //2.创建工厂
        EventFactory<LongEvent> eventFactory = new LongEventFactory();

        //3.创建ringBuffer大小 ,一定要为 2的幂
        int ringBufferSize = 1024*1024;

        //4.创建 Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,ringBufferSize,executor, ProducerType.SINGLE,new YieldingWaitStrategy());

        //5.连接消费者---注册消费者
        disruptor.handleEventsWith(new LongEventHandler());

        //6.启动
        disruptor.start();

        //7.创建RingBuffer容器
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //8.创建生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);

        //9.指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        for (int i = 0; i < 100; i++) {
            byteBuffer.putLong(0,i);
            producer.onData(byteBuffer);
        }

        //10.关闭 disruptor 和 executor
        disruptor.shutdown();
        executor.shutdown();
    }
}
