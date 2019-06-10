package c2_patterns.v9_decorator.Decorator;

import c2_patterns.v9_decorator.component.MonkeyKing;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v9_decorator.Decorator
 *
 * Decorator：实现component接口，并持有 component实例
 * 72变：装饰类
 */
public class Change72 implements MonkeyKing{

    private MonkeyKing mk;

    public Change72(MonkeyKing mk) {
        this.mk = mk;
    }

    @Override
    public void move() {
        mk.move();
    }
}
