package c2_patterns.v7_facade;

import c2_patterns.v7_facade.subsystem.*;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v7_facade
 * 外观类：
 *        向客户提供了简化方法(看电影)，和对现有系统类的方法的委托调用（例如：炸爆米花、放幕布、开灯等）
 */
public class HomeTheaterFacade {
    Popper p;
    Projector pr;
    Screen s;
    TheaterLights t;

    public HomeTheaterFacade(Popper p, Projector pr, Screen s, TheaterLights t) {
        this.p = p;
        this.pr = pr;
        this.s = s;
        this.t = t;
    }

    public void watchMovie(String movie){
        System.out.println("准备看电影");
        p.on();
        t.dim(10);
        s.down();
        pr.on();
        pr.play(movie);


    }

    public void endMovie(){
        t.on();
        s.up();
        pr.off();
    }
}
