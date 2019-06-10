package c2_patterns.v9_decorator.Decorator;

import c2_patterns.v9_decorator.component.MonkeyKing;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v9_decorator.Decorator
 *
 * ConcreteDecorator
 * 鸟：具体的装饰类
 */
public class Bird extends Change72 {

    public Bird(MonkeyKing mk) {
        super(mk);
    }

    @Override
    public void move() {
        System.out.println("变成了鸟在飞");
    }
}
