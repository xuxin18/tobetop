package c2_patterns.v6_adapter.object;

import c2_patterns.v6_adapter.object.d1_target.Duck;
import c2_patterns.v6_adapter.object.d1_target.MallardDuck;
import c2_patterns.v6_adapter.object.d2_adaptee.WildTurkey;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v6_adapter.object
 *
 */
public class Test {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();

        WildTurkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        System.out.println("火鸡");
        turkey.gobble();
        turkey.fly();

        System.out.println("鸭子");
        testDuck(duck);

        System.out.println("假装鸭子的火鸡适配器");
        testDuck(turkeyAdapter);


    }

    static void testDuck(Duck duck){
        duck.quack();
        duck.fly();
    }
}
