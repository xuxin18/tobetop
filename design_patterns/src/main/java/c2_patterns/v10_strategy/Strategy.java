package c2_patterns.v10_strategy;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v10_strategy
 *
 * 定义一组算法
 */
public interface Strategy {
    void algorithm();
}

class StrategyA implements Strategy{
    @Override
    public void algorithm() {
        System.out.println("算法A");
    }
}

class StrategyB implements Strategy{
    @Override
    public void algorithm() {
        System.out.println("算法B");
    }
}

class StrategyC implements Strategy{
    @Override
    public void algorithm() {
        System.out.println("算法C");
    }
}
