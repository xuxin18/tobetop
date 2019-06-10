package c2_patterns.v2_factory.d3_abstractfactory;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v2_factory.d3_abstractfactory
 *
 * 汽车工厂
 */
public interface CarFactory{
    //发动机工厂
    Engine createEngine();
    //座椅工厂
    Chair createChair();
}



