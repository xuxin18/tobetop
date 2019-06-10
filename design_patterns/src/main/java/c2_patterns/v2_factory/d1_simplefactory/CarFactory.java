package c2_patterns.v2_factory.d1_simplefactory;


/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v2_factory.simplefactory
 * @date 20 10:${MIMUTE}
 * @modified
 */
public class CarFactory {

    public static Car createCar(String name){

        if ("奥迪".equals(name)){
            return new Audi();
        }

        if ("吉利".equals(name)){
            return new Jili();
        }

        return null;
    }
}
