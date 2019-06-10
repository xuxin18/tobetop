package c2_patterns.v6_adapter.object;

import c2_patterns.v6_adapter.object.d1_target.Duck;
import c2_patterns.v6_adapter.object.d2_adaptee.Turkey;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v6_adapter.object
 *
 * 假设现在缺鸭子对象，想用一些火鸡对象来冒充。显儿易见，因为火鸡的接口不同，所以我们不能
 * 公然拿来用。那么，写个适配器：将火鸡适配成鸭子。
 *
 * 首先，需要 实现 想转换成的类型接口，也就是客户期望看到的接口 （Duck）
 */
public class TurkeyAdapter implements Duck {

    //对象适配： adaptee 对象为 adapter 所依赖
    Turkey turkey;

    //接着，需要取得适配的对象引用（adaptee），这里我们使用构造器取得这个引用

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    //虽然两个接口都具备了fly()方法，火鸡飞行距离很短，不像鸭子可以长途飞行。
    //要让鸭子的飞行和火鸡能够相对应，必须连续5次调用火鸡的 fly() 来完成
    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
