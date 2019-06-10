package c2_patterns.v6_adapter;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v6_adapter
 * jdk中用到的适配器的例子
 */
public class JDKDemo {
}

/*
    将 Enumeration 适配成 Iterator
 */
class EnumerationIterator implements Iterator{
    Enumeration enumeration;

    public EnumerationIterator(Enumeration enumeration) {
        this.enumeration = enumeration;
    }


    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public Object next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

/*
    jdk1.2开始使用 Iterator 接口
 */
interface Iterator<E>{
    boolean hasNext();
    E next();
    void remove();
}

/*
    早起 java 集合都实现了 一个 elements() 的方法， 返回一个 Enumeration
 */
interface Enumeration<E>{
    boolean hasMoreElements();
    E nextElement();
}
