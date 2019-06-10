package c2_patterns.v11_observe;

import c2_patterns.v11_observe.observe.WechatUser;
import c2_patterns.v11_observe.subject.ConcreteSubject;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v11_observe
 * 微信用户就是观察者，微信公众号是被观察者，有多个的微信用户关注了程序猿这个公众号，当这个公众号更新时就会通知这些订阅的微信用户。
 */
public class Test {
    public static void main(String[] args) {
        //创建具体被观察者
        ConcreteSubject cs = new ConcreteSubject();

        //创建观察者（微信用户）
        WechatUser user1 = new WechatUser("张三");
        WechatUser user2 = new WechatUser("李四");
        WechatUser user3 = new WechatUser("王五");

        //将所有的观察者保存到被观察者的一个集合里 （微信用户订阅了公众号）
        cs.attach(user1);
        cs.attach(user2);
        cs.attach(user3);

        //当 被观察者更新信息后，通知观察者 （公众号更新发出消息给 微信用户）
        cs.notify("海贼王更新了");
    }
}
