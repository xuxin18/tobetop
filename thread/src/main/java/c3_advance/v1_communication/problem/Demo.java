package c3_advance.v1_communication.problem;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c3_advance.v1_communication.problem
 *
 * 一个线程读数据，一个线程写数据，不做任何多线程编程的处理
 *
 * 结果：数据发生错乱，造成线程安全问题
 *
 * 解决方式:给可能发生线程问题的地方加上 synchronized 关键字
 */
public class Demo {
    public static void main(String[] args) {
        Person p = new Person();
        InThread it = new InThread(p);
        OutThread ot = new OutThread(p);
        new Thread(it).start();
        new Thread(ot).start();
    }
}

class Person{
    String name;
    String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

class InThread implements Runnable{

    private Person p;

    public InThread(Person p) {
        this.p = p;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            if (count%2 == 0){
                p.setName("小明");
                p.setSex("男");
            }else {
                p.setName("小红");
                p.setSex("女");
            }
            count++;
        }
    }
}

class OutThread implements Runnable{
    private Person p;

    public OutThread(Person p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(p.getName() + "---" + p.getSex());
        }
    }
}