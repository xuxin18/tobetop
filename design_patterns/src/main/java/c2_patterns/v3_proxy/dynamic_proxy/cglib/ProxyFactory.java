package c2_patterns.v3_proxy.dynamic_proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v3_proxy.dynamic_proxy.cglib
 * Cglib子类代理工厂
 * 对 UserDao 在内存中动态构建一个子类对象
 */
public class ProxyFactory implements MethodInterceptor{

    //维护目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数（参数为：实现了 MethodInterceptor接口的类的实例）
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
    }


    /*
        代理对象 调用对应的方法后，拦截器调用 intercept() 方法，然后由 intercept()方法完成了由 代理对象
            访问到 目标对象 的动态代理实现。
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务");
        Object returnValue = method.invoke(target, objects);//objects 是要目标方法的参数
        System.out.println("提交事务");
        return returnValue;
    }
}
