package c3_advance.v1_communication.problem;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c3_advance.v1_communication.problem
 *
 */
public class Demo2 {

    static final Object lock = new Object();

    public static void main(String[] args) {
        Person p = new Person();
        InThread2 it = new InThread2(p);
        OutThread2 ot = new OutThread2(p);
        new Thread(it).start();
        new Thread(ot).start();
    }
}


class InThread2 implements Runnable{

    private Person p;

    public InThread2(Person p) {
        this.p = p;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            //优化：这个地方的锁对象 最简单的可以是 p （这样做不需要在Demo2中显示的创建一个对象来充当锁对象）
            synchronized (Demo2.lock){
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
}

class OutThread2 implements Runnable{
    private Person p;

    public OutThread2(Person p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true){
            synchronized (Demo2.lock){
                System.out.println(p.getName() + "---" + p.getSex());
            }

        }
    }
}