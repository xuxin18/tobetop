package c2_patterns.v8_prototype.clone.deep;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v8_prototype.clone.shallow
 *
 * todo 浅克隆与申克隆的验证
 */
public class Student implements Cloneable {
    private String name;
    private int age;
    private Double height;
    private Teacher teacher;

    public Student(String name, int age, Double height) {
        this.name = name;
        this.age = age;
        this.height = height;

    }

    public Student(String name, int age, Double height, Teacher teacher) {
        //复用构造函数
        this(name,age,height);

        this.teacher = teacher;
    }

    @Override
    protected Student clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        student.teacher = teacher.clone();
        return student;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Double getHeight() {
        return height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
