package c2_patterns.v2_factory;

import c2_patterns.v2_factory.d1_simplefactory.Car;
import c2_patterns.v2_factory.d1_simplefactory.CarFactory;
import c2_patterns.v2_factory.d2_factorymethod.AudiFactory;
import c2_patterns.v2_factory.d2_factorymethod.JiliFactory;
import c2_patterns.v2_factory.d3_abstractfactory.*;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v2_factory
 * @date 20 10:${MIMUTE}
 * @modified
 */
public class Test {

    public static void main(String[] args) {
        Car audi = CarFactory.createCar("奥迪");
        Car jili = CarFactory.createCar("吉利");
        audi.run();
        jili.run();
        Car benzi = CarFactory.createCar("奔驰");
        Car non = CarFactory.createCar(null);
        System.out.println(benzi);
        System.out.println(non);

        c2_patterns.v2_factory.d2_factorymethod.Car audi2 = new AudiFactory().createCar();
        c2_patterns.v2_factory.d2_factorymethod.Car jili2 = new JiliFactory().createCar();
        audi2.run();
        jili2.run();

        c2_patterns.v2_factory.d3_abstractfactory.CarFactory audiFactory = new c2_patterns.v2_factory.d3_abstractfactory.AudiFactory();
        Engine engine = audiFactory.createEngine();
        engine.run();
        engine.start();

    }
}
