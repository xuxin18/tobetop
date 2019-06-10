package base.c2_base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package base.c2_base
 *
 * 反射的基本运用
 *
 * todo invoke方法的原理 https://www.sczyh30.com/posts/Java/java-reflection-2/
 */
public class ReflectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        /*
            获取类的三种方式
         */
        Class c1 = Class.forName("base.c2_base.EntityTest");
        Class c2 = EntityTest.class;

        EntityTest e = new EntityTest();
        Class c3 = e.getClass();

        /*
            创建实例的2种方式
         */
        //该方法只能调用无参构造函数来创建实例
        Object 哦 = c1.newInstance();

        //获取构造函数信息，参数为构造函数的形参的类
        Constructor cons = c1.getConstructor(String.class);
        //通过构造函数来创建实例。
        Object o2 = cons.newInstance("xuxin");

        /*
            判断是否为某个类的实例
         */
        System.out.println(c1.isInstance(哦));//true
        System.out.println(c2.isInstance(哦));//true

        /*
            方法相关：
                getMethods ：返回某个类的所有 public 方法（包含从父类继承的方法）
                getDeclareMethods：返回某个类或接口的所有方法，包括 public、protected、默认、private方法
                                    但不包括继承的方法
                getMethod：指定返回某个具体的public方法（包含从父类继承的方法）,如果想要获得其他权限修饰符（默认、protected、private）的方法
                            需要使用 getDeclareMethod

                注意：这里获取的方法不包括够造方法
         */
        Method[] m = c1.getMethods();
        System.out.println(Arrays.asList(m));

        Method[] m2 = c1.getDeclaredMethods();
        System.out.println(Arrays.asList(m2));

        //第一个参数为：方法名，后面的参数为：形式参数对应的类，当没有形参时，传 null
        Method m3 = c1.getMethod("cry",null);
        Method m4 = c1.getMethod("smile",String.class);
        Method m5 = c1.getMethod("wait",long.class,int.class);
        System.out.println(m3);
        System.out.println(m4);
        System.out.println(m5);

        /*
            字段相关：
                getFields：
                getDeclareFields：
                getField：
                getDeclareField
                参照 上面的Method
         */


        /*
            invoke: 调用方法
                第一个参数为：方法所属于的实例对象，后面的可变参为 方法中需要传入的参数
         */
        m4.invoke(o2,"高兴");

        /*
            setAccessible(true):
                设置允许访问私有成员,允许调用私有方法
                允许访问 transient 成员
         */
        EntityTest en = (EntityTest) o2;
        Field name = c1.getDeclaredField("name");
        name.setAccessible(true);
        name.set(en,"奥巴马");
        System.out.println(en.getName());
        Method m6 = c1.getDeclaredMethod("speak");
        m6.setAccessible(true);
        m6.invoke(en,null);


        /*
            getReturnType:返回方法的返回值类型
         */
        System.out.println(m6.getReturnType());

        /*
            验证构造函数为 private 时，无法实例化 类
         */
        //Constructor cons2 = c2.getConstructor(String.class,Integer.class);


        /*
            强制获取 private 构造方法
         */
        Constructor[] declaredConstructors = c2.getDeclaredConstructors();
        System.out.println(Arrays.asList(declaredConstructors));

    }

}
