package c2_patterns.v6_adapter.object;

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
        System.out.println("gobble gobble" );
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
