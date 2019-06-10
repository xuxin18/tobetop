package c2_patterns.v11_observe.subject;

import c2_patterns.v11_observe.observe.Observe;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v11_observe.subject
 * 抽象主题（被观察者）：把所有观察者对象保存在一个 集合 里，每个主题都可以有任意数量的观察者
 *                    可以 增加 和 删除 观察者对象
 *                    向观察者发送通知
 *
 */
public interface Subject {

    //增加订阅者
    public void attach(Observe observe);

    //删除订阅者
    public void detach(Observe observe);

    //通知 订阅者（观察者）更新消息
    public void notify(String message);
}
