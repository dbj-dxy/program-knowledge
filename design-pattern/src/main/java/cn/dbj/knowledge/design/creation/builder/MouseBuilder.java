package cn.dbj.knowledge.design.creation.builder;

/**
 * @author DBJ
 * @date 2020-08-20
 * 具体建造者
 */
public class MouseBuilder extends BaseBuilder {
    @Override
    public void buildScreen() {
    }

    @Override
    public void buildKeyboard() {
    }

    @Override
    public void buildMouse() {
        product.setMouse("达尔优鼠标");
    }
}
