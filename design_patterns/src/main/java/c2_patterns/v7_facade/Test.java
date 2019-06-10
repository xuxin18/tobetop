package c2_patterns.v7_facade;

import c2_patterns.v7_facade.subsystem.Popper;
import c2_patterns.v7_facade.subsystem.Projector;
import c2_patterns.v7_facade.subsystem.Screen;
import c2_patterns.v7_facade.subsystem.TheaterLights;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v7_facade
 * @date 21 18:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) {
        Popper p = new Popper();
        Projector pr = new Projector();
        Screen s = new Screen();
        TheaterLights t = new TheaterLights();

        HomeTheaterFacade htf = new HomeTheaterFacade(p,pr,s,t);
        System.out.println("张三想要看电影");
        htf.watchMovie("阿凡达");

        System.out.println("电影看完了");
        htf.endMovie();


    }
}
