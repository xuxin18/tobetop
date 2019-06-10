package c2_patterns.v6_adapter.object.d2_adaptee;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v6_adapter.object
 * 野生火鸡
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("咯咯" );
    }

    @Override
    public void fly() {
        System.out.println("火鸡一次飞100m");
    }
}
