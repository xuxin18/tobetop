package c3_myaop1.aspect;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c3_myaop1.aspect
 * @date 12 14:${MIMUTE}
 * @modified
 *
 * 切面类 ： 用于存放通知
 */
public class MyAspect {

    public void before(){
        System.out.println("先。。。");
    }

    public void after(){
        System.out.println("后。。。");
    }
}
