package c2_patterns.v5_template;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v5_template
 *
 * 具体模板
 */
public class Baocai extends Cooking {
    @Override
    protected void pourVegetable() {
        System.out.println("将包菜放入锅中");
    }

    @Override
    protected void pourSauce() {
        System.out.println("加入 辣椒、醋、盐");
    }
}
