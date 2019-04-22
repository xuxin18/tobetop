package c3_myaop1.beanfactory;


import c3_myaop1.aspect.MyAspect;
import c3_myaop1.service.UserService;
import c3_myaop1.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c3_myaop1.beanfactory
 * @date 12 14:${MIMUTE}
 * @modified
 * 编写工厂类生成代理
 */
public class MyBeanFactory {

    public static UserService createService(){
        //目标类
        UserService target = new UserServiceImpl();

        //切面类
        final MyAspect myAspect = new MyAspect();

        //代理类： 将 目标类（切入点） 和 切面类（通知）结合 ---> 切面

        return (UserService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //目标方法执行前，执行before通知
                        myAspect.before();
                        //执行目标方法
                        Object returnValue = method.invoke(target, args);

                        //目标方法执行后，执行after通知
                        myAspect.after();

                        return returnValue;
                    }
                });
    }

}
