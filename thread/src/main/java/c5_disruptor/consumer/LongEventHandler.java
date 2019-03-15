package c5_disruptor.consumer;


import c5_disruptor.entity.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c5_disruptor
 *
 * 事件消费者，也就是一个事件处理器。这个事件处理器简单的把事件中存储的数据打印到终端
 */
public class LongEventHandler implements EventHandler<LongEvent>{

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者：" + event.getValue());
    }
}
