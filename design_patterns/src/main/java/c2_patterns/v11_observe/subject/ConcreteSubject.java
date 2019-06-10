package c2_patterns.v11_observe.subject;

import c2_patterns.v11_observe.observe.Observe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v11_observe.subject
 * 具体被观察者
 */
public class ConcreteSubject implements Subject {

    //储存 观察者（订阅了公众号的微信用户）
    private List<Observe> wechatUserList = new ArrayList<>();

    @Override
    public void attach(Observe observe) {
        wechatUserList.add(observe);
    }

    @Override
    public void detach(Observe observe) {
        wechatUserList.remove(observe);
    }

    @Override
    public void notify(String message) {
        for (Observe o : wechatUserList){
            o.update(message);
        }
    }
}
