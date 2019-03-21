package c2_patterns.v4_builder;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v4_builder
 *
 * 实现 Builder 接口，针对不同的商业逻辑，具体化复杂对象的各部分的创建，在建造完成后，提供产品的实例
 */
public class ManBuilder implements PersonBuilder{

    private Person person;



    @Override
    public void buildHead() {

    }

    @Override
    public void buildBody() {

    }

    @Override
    public void buildFoot() {

    }

    @Override
    public Person BuildPerson() {
        return null;
    }
}
