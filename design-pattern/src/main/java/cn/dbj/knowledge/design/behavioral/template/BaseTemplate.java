package cn.dbj.knowledge.design.behavioral.template;

/**
 * @author DBJ
 * @date 2020-08-24
 */
public abstract class BaseTemplate {

    /**
     * description 模板方法，限定模板执行骨架
     * @author DBJ
     * @date 2020-08-24 17:02
     */
    public final void templateMethod(){
        openFire();
        food();
        cooking();
        condiment();
        if (needParsley()) {
            addParsley();
        }
    }

    public void openFire() {
        System.out.println("点火");
    }

    public void cooking() {
        System.out.println("炒菜");
    }

    public boolean needParsley() {
        return true;
    }

    public void addParsley() {
        System.out.println("加香菜");
    }

    abstract void condiment();
    abstract void food();
}
