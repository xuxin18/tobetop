package c5_disruptor.factory;

import c5_disruptor.entity.LongEvent;
import com.lmax.disruptor.EventFactory;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c5_disruptor
 *
 * 让 Disruptor 为我们创建事件，我们同时还声明了一个 EventFactory 来实例化Event对象
 */
public class LongEventFactory implements EventFactory<LongEvent>{
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
