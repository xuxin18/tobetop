package c2_patterns.v1_singleton;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.singleton
 *
 * 静态内部类单例
 */
public class Singleton4 {

    private Singleton4(){}

    public static Singleton4 getInstance(){
        return SingletonHolder.instance;
    }

    /*
        private的静态内部类无法被外部访问，只有当调用 Singleton4.getInstance() 的时候才能得到单例对象 instance

        instance对象初始化的时机并不是在单例类Singleton被加载的时候，而是在调用 getInstance 方法时，
        使得静态内部类 SingletonHolder 被加载的时候。（这种是利用classloader 的加载机制来实现懒加载。并保证构建单例的线程安全）
     */
    private static class SingletonHolder{
        private static final Singleton4 instance = new Singleton4();
    }
}
