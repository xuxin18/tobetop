package c2_patterns.v8_prototype.clone.deep;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v8_prototype.clone.shallow
 * @date 22 15:${MIMUTE}
 * @modified
 */
public class Teacher implements Cloneable {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    protected Teacher clone() throws CloneNotSupportedException {
        return (Teacher) super.clone();
    }
}
