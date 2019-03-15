package c5_disruptor.producer;

import c5_disruptor.entity.LongEvent;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c5_disruptor
 *
 * 定义生产者发送事件
 */
public class LongEventProducer {

    public final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer){
        //1.ringBuffer 获取事件在队列中的下标位置
        long sequence = ringBuffer.next();

        Long data = null;

        try {
            //2.取出空的事件队列
            LongEvent longEvent = ringBuffer.get(sequence);
            data = byteBuffer.getLong(0);

            //3.获取事件队列传递的数据
            longEvent.setValue(data);

            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者准备发送数据");

            //4.发布事件
            ringBuffer.publish(sequence);
        }
    }
}
