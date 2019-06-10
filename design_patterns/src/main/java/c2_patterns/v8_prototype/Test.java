package c2_patterns.v8_prototype;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v8_prototype
 * @date 22 14:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        ConcreteBook cb = new ConcreteBook();
        for (int i = 0; i < 5; i++) {
            ConcreteBook copeBook = (ConcreteBook) cb.clone();
            copeBook.show();
            System.out.println(copeBook);
        }
    }
}
