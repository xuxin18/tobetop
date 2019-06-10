package c2_patterns.v10_strategy;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v10_strategy
 *
 * 封装类：也叫做 上下文，对策略进行二次封装，目的是避免高层模块对策略的直接调用
 */
public class Context {
    private Strategy s;

    public Context(Strategy s) {
        this.s = s;
    }

    public void execute(){
        s.algorithm();
    }
}
