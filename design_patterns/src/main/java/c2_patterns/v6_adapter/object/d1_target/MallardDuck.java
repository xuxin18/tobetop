package c2_patterns.v6_adapter.object;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v6_adapter.object
 *
 * 绿头鸭是鸭子的子类，实现了 鸭子的 嘎嘎叫和飞行能力
 */
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("quack");
    }

    @Override
    public void fly() {
        System.out.println("flying");
    }
}
