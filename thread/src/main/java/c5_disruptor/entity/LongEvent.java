package c5_disruptor.entity;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c5_disruptor
 * 定义事件 event，通过 Disruptor 进行交换的数据类型
 */
public class LongEvent {

    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
