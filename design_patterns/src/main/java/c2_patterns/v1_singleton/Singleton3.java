package c2_patterns.singleton;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.singleton
 *
 * 双重检测锁+volatile 模式
 */
public class Singleton3 {

    private Singleton3(){

    }

    private static volatile Singleton3 instance = null;

    public static Singleton3 getInstance(){
        if (instance == null){
            synchronized (Singleton3.class){
                if (instance == null){
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
