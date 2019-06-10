package c2_patterns.v3_proxy.dynamic_proxy.cglib;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v3_proxy
 * 目标对象
 */
public class UserDao{
    public static void save() {
        System.out.println("---已经保存数据---");
    }
}
