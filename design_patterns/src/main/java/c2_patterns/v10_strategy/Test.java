package c2_patterns.v10_strategy;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v10_strategy
 * @date 22 17:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) {
        Context context;
        System.out.println("执行策略A");
        context = new Context(new StrategyA());
        context.execute();

        System.out.println("执行策略B");
        context = new Context(new StrategyB());
        context.execute();
    }
}
