package c2_patterns.v1_singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.singleton
 *
 * 利用反射打破单例设计模式
 */
public class Test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<Singleton4> con = Singleton4.class.getDeclaredConstructor();
        con.setAccessible(true);
        Singleton4 s1 = con.newInstance();
        Singleton4 s2 = con.newInstance();
        System.out.println(s1);
        System.out.println(s2);

        Constructor<Singleton6> con1 = Singleton6.class.getDeclaredConstructor();
        con1.setAccessible(true);
        Singleton6 s61 = con1.newInstance();
        System.out.println(s61);
        Field flag = Singleton6.class.getDeclaredField("flag");
        flag.setAccessible(true);
        flag.set(Singleton6.class,false);
        Singleton6 s62 = con1.newInstance();
        System.out.println(s62);


    }
}
