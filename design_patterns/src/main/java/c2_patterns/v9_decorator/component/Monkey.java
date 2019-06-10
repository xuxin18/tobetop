package c2_patterns.v9_decorator.component;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v9_decorator.component
 * ConcreteComponent
 * 猴子：被装饰的类
 */
public class Monkey implements MonkeyKing {
    @Override
    public void move() {
        System.out.println("猴子在移动");
    }
}
