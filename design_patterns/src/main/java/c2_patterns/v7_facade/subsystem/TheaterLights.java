package c2_patterns.v7_facade.subsystem;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v7_facade.subsystem
 * 灯光
 */
public class TheaterLights {
    public void dim(int i) {
        System.out.println("将光线亮度调到" + i);
    }

    public void on() {
        System.out.println("打开灯光");
    }
}
