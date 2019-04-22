package com.mvc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.mvc
 * @date 22 11:${MIMUTE}
 * @modified
 *
 * 手写 springmvc原理分析：
 *      1.创建一个前端控制器 MyDispacherServlet 拦截所有请求（springmvc 基于 servlet实现）
 *      2.重写 Servlet 的 init 方法（初始化操作）
 *          将扫包范围内的所有的类，注入到 springmvc容器里面，存放Map集合中 key 为默认类名小写，value为对象
 *          将 url 映射和方法进行关联
 *              判断类上是否有注解，使用java反射机制循环遍历方法，判断方法上是否存在注解，进行封装url和方法对应
 *      3.处理请求，重写 get 或者 post 方法
 *          获取请求urk，从urlBeans集合中获取实例对象
 *          获取实例对象成功后，调用 urlMethods集合获取方法名称，使用反射机制执行
 */
public class Test {
    //springmvc容器对象：key 为 类名id；value为对象
    private ConcurrentHashMap<String, Object> springmvcBeans = new ConcurrentHashMap<>();

    //springmvc容器对象：key 为 请求地址；value 为 类
    private ConcurrentHashMap<String, Object> urlBeans = new ConcurrentHashMap<>();

    //springmvc容器对象：key 为 请求地址； value 为 方法名称
    private ConcurrentHashMap<String, Object> urlMethods = new ConcurrentHashMap<>();
}
