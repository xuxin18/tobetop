package c2_patterns.v5_template;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v5_template
 * @date 21 10:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) {
        Cooking bc = new Baocai();
        bc.cookProcess();

        Caixin cx = new Caixin();
        cx.cookProcess();
    }
}
