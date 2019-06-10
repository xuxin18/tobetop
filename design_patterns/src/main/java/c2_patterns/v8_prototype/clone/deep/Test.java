package c2_patterns.v8_prototype.clone.deep;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v8_prototype.clone.shallow
 * 如果在克隆一个对象时，要想让这个克隆对象和源对象完全彼此独立，那么在引用链上的每一集对象都要被显示的克隆。
 *      所以创建彻底的深克隆是非常麻烦的，尤其是在引用关系非常复杂的情况下，或者在引用链的某一级上引用了
 *      第三方的对象，而这个第三方对象没有实现 clone 方法，那么在它之后的所有引用都是被共享的（浅克隆）
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher t = new Teacher("英语王老师");

        Student m = new Student("小明", 18,1.68,t);
        Student cloneM = m.clone();
        System.out.println(m == cloneM);
        //由 m.getTeacher() == cloneM.getTeacher() 返回 false知：此时源对象中的引用对象 在clone 时是深克隆
        System.out.println(m.getTeacher() == cloneM.getTeacher());




    }
}
