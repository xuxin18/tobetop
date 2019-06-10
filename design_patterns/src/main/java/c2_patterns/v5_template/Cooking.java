package c2_patterns.v5_template;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_patterns.v5_template
 *
 * 抽象模板：将相同的操作放在抽象父类中，而将不同的部分让子类去实现
 *
 * 做菜：抽象模板用来控制炒菜的流程，具体子类用来 实现做不同的菜
 */
public abstract class Cooking {
    /*
        倒油、热油、放入蔬菜、翻炒、加调料、出锅
     */
    final void cookProcess(){
        pourOil();
        heatOil();
        pourVegetable();
        fry();
        pourSauce();
        success();
    }

    protected void success() {
        System.out.println("完成，出锅");
    }

    /* 流程中不能确定 需要子类具体实现*/
    abstract void pourSauce();

    protected void fry() {
        System.out.println("翻炒");
    }

    /* 流程中不能确定 需要子类具体实现*/
    abstract void pourVegetable();

    protected void heatOil() {
        System.out.println("热油");
    }

    protected void pourOil() {
        System.out.println("倒油");
    }
}
