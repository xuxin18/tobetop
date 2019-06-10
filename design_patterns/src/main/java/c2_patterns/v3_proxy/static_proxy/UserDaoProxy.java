package c2_patterns.v3_proxy.static_proxy;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v3_proxy
 * 代理类
 *
 * 代理类需要和目标类实现相同的接口
 */
public class UserDaoProxy implements IUserDao {

    //接收保存目标对象
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开启事务...");
        target.save();
        System.out.println("提交事务...");
    }
}
