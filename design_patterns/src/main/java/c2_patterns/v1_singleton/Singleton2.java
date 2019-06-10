package c2_patterns.v1_singleton;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.singleton
 *
 * 饿汉模式
 */
public class Singleton2 {

    //私有化构造函数
    private Singleton2(){

    }

    //声明 并创建 实例
    private static Singleton2 instance = new Singleton2();

    //返回实例
    public static Singleton2 getInstance(){
        return instance;
    }
}
