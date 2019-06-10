package c2_patterns.v4_builder;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v4_builder
 *
 * 指导者
 * 调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体的产品信息，只负责保证对象各部分完
 *      完整创建或按某周顺序创建
 */
public class PersonDirector {

    //要求先建头，再身体，再下肢
    public Person constructPerson(PersonBuilder pb){
        pb.buildHead();
        pb.buildBody();
        pb.buildFoot();
        return pb.BuildPerson();
    }
}
