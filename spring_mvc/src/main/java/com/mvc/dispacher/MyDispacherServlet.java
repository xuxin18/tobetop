package com.mvc.dispacher;

import com.mvc.annotation.MyController;
import com.mvc.annotation.MyRequestMapping;
import com.mvc.util.ClassUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.mvc.dispacher
 * @date 22 14:${MIMUTE}
 * @modified
 * 自定义 dispacherServlet
 */
public class MyDispacherServlet extends HttpServlet{

    //springmvc容器对象：key 为 类名id；value为对象
    private ConcurrentHashMap<String, Object> springmvcBeans = new ConcurrentHashMap<>();

    //springmvc容器对象：key 为 请求地址；value 为 类
    private ConcurrentHashMap<String, Object> urlBeans = new ConcurrentHashMap<>();

    //springmvc容器对象：key 为 请求地址； value 为 方法名称(这个地方没有考虑形参，如果考虑形参则将第二个 String 改为：ConcurrentHashMap<String, Object> --- Object 为参数类型)
    private ConcurrentHashMap<String, String> urlMethods = new ConcurrentHashMap<>();


    @Override
    public void init() throws ServletException {
        //获取当前包下所有的类
        List<Class<?>> classes = ClassUtil.getClasses("com.mvc.controller");

        //将扫包范围内的所有的类，注入到 springmvc容器里面，存放Map集合中 key 为默认类名小写，value为对象
        try {
            findClassByMVCAnno(classes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将 url 映射和方法进行关联
        handlerMapping();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求
        //1. 获取请求url地址
        String requestURI = req.getRequestURI();
        if (StringUtils.isEmpty(requestURI)){
            return;
        }

        //2. 从Map集合中获取控制对象
        Object o = urlBeans.get(requestURI);
        if (o == null){
            resp.getWriter().println("404, not found url");
            return;
        }
        //3. 使用url地址获取方法
        String methodName = urlMethods.get(requestURI);
        if (StringUtils.isEmpty(methodName)){
            resp.getWriter().println("not found method");
        }
        //4. 使用java的反射机制调用方法,返回结果
        Object result = null;
        try {
            Class<?> clazz = o.getClass();
            Method method = clazz.getMethod(methodName);

            result = method.invoke(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //5. 调用视图转换器渲染返回给页面展示
        myResourceViewResolver((String)result, req, resp);
    }

    private void myResourceViewResolver(String pageName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prefix = "/";
        String suffix = ".jsp";
        req.getRequestDispatcher(prefix + pageName + suffix).forward(req, resp);
    }

    private void findClassByMVCAnno(List<Class<?>> classes) throws InstantiationException, IllegalAccessException {
        for (Class<?> clazz : classes){
            //获取类上指定注解
            MyController annotation = clazz.getDeclaredAnnotation(MyController.class);
            if (annotation != null){
                //beanID 默认为 类名小写
                String beanID = ClassUtil.toLowerCaseFirstOne(clazz.getSimpleName());
                //实例化对象
                Object o = ClassUtil.newInstance(clazz);
                springmvcBeans.put(beanID,o);
            }
        }
    }

    private void handlerMapping(){
        //1.遍历 springmvc bean容器判断类上 是否有 url映射注解
        for (Map.Entry<String, Object> mvcBean: springmvcBeans.entrySet()){


            //2.1 获取类文件
            Object o = mvcBean.getValue();
            Class<?> clazz = o.getClass();
            //2.2 获取类文件上是否有 注解
            MyRequestMapping annotation = clazz.getDeclaredAnnotation(MyRequestMapping.class);
            String baseUrl = "";
            if (annotation != null){
                //如果类上有url 注解，则获取类的url映射地址
                baseUrl = annotation.value();
            }

            //3.1 获取当前类的所有方法（不包括继承的方法）
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method m : declaredMethods){
                //3.2 判断方法上是否有 url映射注解
                MyRequestMapping methodAnno = m.getDeclaredAnnotation(MyRequestMapping.class);
                if (methodAnno != null){
                    String methodUrl = baseUrl + methodAnno.value();
                    //key 为 请求地址；value 为 类
                    urlBeans.put(methodUrl, o);
                    //key 为 请求地址； value 为 方法名称
                    urlMethods.put(methodUrl,m.getName());
                }
            }

        }
    }
}
