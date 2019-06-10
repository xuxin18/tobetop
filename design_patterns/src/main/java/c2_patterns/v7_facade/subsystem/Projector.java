package c2_patterns.v7_facade.subsystem;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v7_facade.subsystem
 * 投影仪
 */
public class Projector {
    public void on() {
        System.out.println("打开投影仪");
    }

    public void play(String movie) {
        System.out.println("投影仪开始放电影：" + movie);
    }

    public void off() {
        System.out.println("关闭投影仪");
    }
}
