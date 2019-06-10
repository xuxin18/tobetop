package c2_patterns.v6_adapter.object.d2_adaptee;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v6_adapter.object
 * 火鸡基类
 */
public interface Turkey {

    //火鸡不会嘎嘎叫，只会咯咯叫
    public void gobble();

    //火鸡虽然会飞，但是飞不远
    public void fly();
}
