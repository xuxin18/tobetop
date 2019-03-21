package c2_patterns.singleton;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.singleton
 *
 * 懒汉模式
 */
public class Singleton1 {

    //私有化构造函数
    private Singleton1(){

    }

    //声明 实例为 null
    private static Singleton1 instance = null;

    //返回实例
    public static Singleton1 getInstance(){
        //如果实例为 null，则创建，否则返回
        if (instance == null){
            instance = new Singleton1();
        }
        return instance;
    }
}
