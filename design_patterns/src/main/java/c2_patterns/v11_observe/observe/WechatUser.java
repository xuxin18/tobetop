package c2_patterns.v11_observe.observe;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v11_observe.observe
 * 具体观察者
 */
public class WechatUser implements Observe{

    //微信用户名
    private String name;

    public WechatUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "-" + message );
    }
}
