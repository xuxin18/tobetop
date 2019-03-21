package c2_patterns.v2_factory.simplefactory;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v2_factory.simplefactory
 *
 *
 */
public interface Car {
    public void run();
}

class Audi implements Car{
    @Override
    public void run() {
        System.out.println("奥迪车在跑...");
    }
}

class Jili implements Car{
    @Override
    public void run() {
        System.out.println("吉利车在跑...");
    }
}
