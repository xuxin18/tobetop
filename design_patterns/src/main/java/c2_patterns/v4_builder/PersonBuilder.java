package c2_patterns.v4_builder;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v4_builder
 * Builder：给出一个抽象接口，以规范产品对象的各个组成成分的创造，并不涉及到具体的对象部件的创建
 */
public interface PersonBuilder {

    void buildHead();
    void buildBody();
    void buildFoot();

    //组装
    Person BuildPerson();
}
