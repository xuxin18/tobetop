package sql;

import orm.mybatis_aop.MyInvocationHandlerOfMyBatis;

import java.lang.reflect.Proxy;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package sql
 * @date 24 15:${MIMUTE}
 * @modified
 */
public class SqlSession {

    /*
    <T>的作用：声明该方法是个 泛型方法。
        编译器并不认识 T 是泛型类型占位符。<T> 就会告诉编译器，现在声明的 T 是一个泛型类型的占位符，而不是其他的什么（如：类名）
    */
    //加载mapper接口（通过动态代理返回 代理对象）
    public static <T> T getMapper(Class<?> c) {
        return (T)Proxy.newProxyInstance(
                c.getClassLoader(),
                new Class[]{c},
                new MyInvocationHandlerOfMyBatis(c));
    }
}
