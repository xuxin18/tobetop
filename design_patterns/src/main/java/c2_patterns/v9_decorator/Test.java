package c2_patterns.v9_decorator;

import c2_patterns.v9_decorator.Decorator.Bird;
import c2_patterns.v9_decorator.Decorator.Fish;
import c2_patterns.v9_decorator.component.Monkey;
import c2_patterns.v9_decorator.component.MonkeyKing;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v9_decorator
 *
 * todo https://www.cnblogs.com/java-my-life/archive/2012/04/20/2455726.html
 * 对半透明进行分析
 *
 * https://www.cnblogs.com/xrq730/p/4908940.html
 *
 * 另外有时间也要结合 head first 里的例子来加深理解下
 *
 *
 */
public class Test {
    public static void main(String[] args) {
        MonkeyKing mk = new Monkey();

        MonkeyKing bird = new Bird(mk);
        bird.move();
        MonkeyKing fish = new Fish(mk);
        fish.move();
    }
}
