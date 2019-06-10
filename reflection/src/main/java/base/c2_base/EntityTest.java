package base.c2_base;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package base.c2_base
 * @date 19 10:${MIMUTE}
 * @modified
 */
public class EntityTest {
    private String name;

    public Integer age;

    public EntityTest() {
    }

    public EntityTest(String name) {

        this.name = name;
    }

    private EntityTest(String name, Integer age) {

        this.name = name;
        this.age = age;
    }

    public void smile(String name){
        System.out.println(name + "哈");
    }
    public void cry(){
        System.out.println("呜");
    }

    protected void sing(){
        System.out.println("呜");
    }

    private void speak(){
        System.out.println("爱你哟");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
