package c2_patterns.v2_factory.d3_abstractfactory;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v2_factory.d3_abstractfactory
 * @date 20 14:${MIMUTE}
 * @modified
 */
public class EngineA implements Engine{
    @Override
    public void run() {
        System.out.println("转的快！");
    }

    @Override
    public void start() {
        System.out.println("自动档，启动快");
    }
}
