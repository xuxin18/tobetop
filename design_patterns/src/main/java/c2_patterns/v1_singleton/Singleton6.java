package c2_patterns.singleton;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.singleton
 *
 * 防反射(其实也不能防止暴力反射，见Test里的测试)
 */
public class Singleton6 {

    private static boolean flag = false;
    private Singleton6(){
        if (flag == false){
            flag = !flag;
        }else {
            throw new RuntimeException("单例模式被侵犯！");
        }
    }

    public static Singleton6 getInstance(){
        return SingletonHolder.instance;
    }


    private static class SingletonHolder{
        private static Singleton6 instance = new Singleton6();
    }
}
