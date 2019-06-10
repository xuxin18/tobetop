package c2_patterns.v4_builder;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v4_builder
 *
 * 具体建造者
 * 实现 Builder 接口，针对不同的商业逻辑，具体化复杂对象的各部分的创建，在建造完成后，提供产品的实例
 */
public class ManBuilder implements PersonBuilder{

    private Person person;

    public ManBuilder() {
       person = new Person();
    }

    @Override
    public void buildHead() {
        person.setHead("建造者头部分");
    }

    @Override
    public void buildBody() {
        person.setBody("建造者身体部分");
    }

    @Override
    public void buildFoot() {
        person.setFoot("建造者下肢部分");
    }

    @Override
    public Person BuildPerson() {
        return person;
    }
}
