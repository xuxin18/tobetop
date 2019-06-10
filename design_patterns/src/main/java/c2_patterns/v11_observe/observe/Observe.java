package c2_patterns.v11_observe.observe;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v11_observe.observe
 * 抽象观察者：定义更新接口，使得在 获得主题更改通知时 更新自己
 */
public interface Observe {
    public void update(String message);
}
