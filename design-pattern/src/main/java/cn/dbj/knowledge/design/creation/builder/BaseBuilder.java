package cn.dbj.knowledge.design.creation.builder;

/**
 * @author DBJ
 * @date 2020-08-20
 * 抽象建造
 */
public abstract class BaseBuilder {

    protected Product product = new Product();

    public abstract void buildScreen();
    public abstract void buildKeyboard();
    public abstract void buildMouse();

    public Product getResult () {
        return product;
    }


}
