/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package PACKAGE_NAME
 * @date 03 15:${MIMUTE}
 * @modified
 * this 详细解析：this最核心的内容其实就是：作为一个指针指向了调用当前方法的对象。
 *    在jvm中，jvm认为在 类加载流程中，在进行加载和链接流程后，对象就已经创建成功了（此时 this 指针已经创建成功了），而
 *    从 java程序的视角来看，对象创建才刚刚开始，<init>方法（也就是实例构造器方法，即构造函数）还没有执行，所有的字段还
 *    都为零值。
 */
public class Test {
    public static void main(String[] args) {
        Person p = new Person("张三");
        p.print();
    }
}

class Person{
    String name = "wangwu";

    public Person(String name) {
        this.name = name;
    }

    public void print(){
        String name = "lisi";
        System.out.println(this.name); // 如果没有上一行代码中的 name 干扰的话，this可以省略
        System.out.println(name);
    }
}
