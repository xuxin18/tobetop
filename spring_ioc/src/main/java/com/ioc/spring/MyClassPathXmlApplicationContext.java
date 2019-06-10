package com.ioc.spring;

import com.ioc.anno.MyResource;
import com.ioc.anno.MyService;
import com.ioc.utils.ClassUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.ioc.spring
 * @date 16 15:${MIMUTE}
 * @modified
 * 注解版本注入 bean
 *
 * 思路：
 *   1.使用java反射机制扫包，获取当前包下所有的类
 *   2.判断类上是否存在注入bean的注解
 *   3.使用java反射机制进行初始化
 */
public class MyClassPathXmlApplicationContext {

    //自定义扫包范围
    private String packageName;

    //spring bean容器
    private static ConcurrentHashMap<String, Object> beansMap;

    public MyClassPathXmlApplicationContext(String packageName) throws Exception {
        this.packageName = packageName;
        beansMap = new ConcurrentHashMap<>();
        initBeans();

        initField();


    }

    //初始化属性
    private void initField() throws Exception {
        //初始化容器后，遍历spring容器中的 bean对象，给属性赋值
        for (Map.Entry<String, Object> entry : beansMap.entrySet()){
            Object bean = entry.getValue();
            getFieldByAnno(bean);

        }
    }

    //初始化spring bean容器
    public void initBeans() throws Exception {
        //1.使用java反射机制扫包，获取当前包下所有的类
        List<Class<?>> classes = ClassUtil.getClasses(packageName);
        //2.判断类上是否存在注入bean的注解,将存在 注解的类返回
        ConcurrentHashMap<String, Object> classesByAnno = findClassesByAnno(classes);
        if (classesByAnno == null || classesByAnno.isEmpty()){
            throw new Exception("该包下没有任何类上存在MyService注解");
        }
    }

    public Object getBean(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)){
            throw new Exception("beanId 参数不能为空");
        }
        Object o = beansMap.get(beanId);
        if (o == null){
            throw new Exception("指定的" + beanId + "对象未找到");
        }
        return o;
    }


    //判断类上是否存在注入bean的注解
    public ConcurrentHashMap<String, Object> findClassesByAnno(List<Class<?>> classes) throws InstantiationException, IllegalAccessException {
        for (Class<?> clazz : classes){
            //判断类上是否有指定注解
            MyService annotation = clazz.getAnnotation(MyService.class);
            if (annotation != null){
                //获取当前类名
                String className = clazz.getSimpleName();
                String beanId = toLowerCaseFirstOne(className);

                //3. 通过反射机制初始化对象,并注入容器
                beansMap.put(beanId, clazz.newInstance());
            }
        }
        return beansMap;
    }

    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    //判断属性上是否有指定注解 (依赖注入注解原理（相当于 @Autowired）)
    public void getFieldByAnno(Object o) throws Exception {
        //1.使用反射机制，获取当前类的所有属性
        Field[] declaredFields = o.getClass().getDeclaredFields();
        //2.判断 属性 上是否存在指定的注解
        for (Field field : declaredFields){
            MyResource myResource = field.getAnnotation(MyResource.class);
            if (myResource != null){
                String fieldName = field.getName();
                //根据属性名称获取 bean
                Object bean = getBean(fieldName);
                if (bean != null){
                    field.setAccessible(true);
                    //给对象o的属性field赋值
                    field.set(o, bean);
                }
            }
        }
    }
}
