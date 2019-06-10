package c2_patterns.v3_proxy;

import c2_patterns.v3_proxy.dynamic_proxy.jdk.IUserDao;
import c2_patterns.v3_proxy.dynamic_proxy.jdk.ProxyFactory;
import c2_patterns.v3_proxy.static_proxy.UserDao;
import c2_patterns.v3_proxy.static_proxy.UserDaoProxy;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v3_proxy
 * @date 20 15:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //代理对象，把目标对象传给代理对象，建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);

        //执行代理的方法
        proxy.save();


        //目标对象
        c2_patterns.v3_proxy.dynamic_proxy.jdk.IUserDao targetJ = new c2_patterns.v3_proxy.dynamic_proxy.jdk.UserDao();
        //原始的类型
        System.out.println(targetJ.getClass());
        //给目标对象，创建代理对象
        IUserDao proxyJ = (IUserDao) new ProxyFactory(targetJ).getProxyInstance();
        System.out.println(proxyJ.getClass());
        //代理类执行方法
        proxyJ.save();


        //目标对象
        c2_patterns.v3_proxy.dynamic_proxy.cglib.UserDao targetC = new c2_patterns.v3_proxy.dynamic_proxy.cglib.UserDao();
        //代理对象
        c2_patterns.v3_proxy.dynamic_proxy.cglib.UserDao proxyC = (c2_patterns.v3_proxy.dynamic_proxy.cglib.UserDao) new c2_patterns.v3_proxy.dynamic_proxy.cglib.ProxyFactory(targetC).getProxyInstance();
        //执行代理对象的方法
        proxyC.save();
    }
}
