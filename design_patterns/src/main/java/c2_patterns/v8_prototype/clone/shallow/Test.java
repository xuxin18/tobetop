package c2_patterns.v8_prototype.clone.shallow;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v8_prototype.clone.shallow
 * @date 22 15:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher t = new Teacher("英语王老师");

        Student s = new Student("小明", 18,1.68);
        Student cloneS = s.clone();

        //通过打印的 s 和 cloneS 的值不同 可以看出 s 和 cloneS 在堆中是两个不同的对象
        System.out.println(s);
        System.out.println(cloneS);


        Student m = new Student("小明", 18,1.68,t);
        Student cloneM = m.clone();
        System.out.println(m == cloneM);
        //由 m.getTeacher() == cloneM.getTeacher() 返回 true 知：源对象中的引用对象 默认在clone 时是浅克隆
        //那么如何实现深克隆呢？见 deep 包中的示例
        System.out.println(m.getTeacher() == cloneM.getTeacher());




    }
}
