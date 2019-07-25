import mapper.UserMapper;
import sql.SqlSession;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package PACKAGE_NAME
 * @date 24 11:${MIMUTE}
 * @modified
 *
 * mybatis的3个难点：
 *      1.接口既然不能被实例化，那么我们是怎么能够调用接口的方法呢？
 *          方式一：匿名内部类
 *          方式二：字节码技术虚拟生产子类
 *          方式三：mybatis是 使用 动态代理技术
 *      2.参数如何和sql参数绑定
 *      3.返回结果
 *
 *  todo https://blog.csdn.net/luanlouis/article/details/40422941
 *  https://www.cnblogs.com/dongying/p/4142476.html
 *
 *
 */
public class Test {
    public static void main(String[] args) {
        //根据动态代理技术，返回UserMapper接口的代理对象
        UserMapper proxy = SqlSession.getMapper(UserMapper.class);
        int i = proxy.insertUser("zhangsan", 11);
        System.out.println("insertResult:" + i);

    }
}
