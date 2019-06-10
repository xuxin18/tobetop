package c2_patterns.v8_prototype;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v8_prototype
 * 原型类 prototype
 */
public class PrototypeBook implements Cloneable{

    @Override
    protected PrototypeBook clone() throws CloneNotSupportedException {
        PrototypeBook p = (PrototypeBook) super.clone();
        return p;
    }
}

class ConcreteBook extends PrototypeBook{
    public void  show(){
        System.out.println("原型模式的具体实现类");
    }
}
