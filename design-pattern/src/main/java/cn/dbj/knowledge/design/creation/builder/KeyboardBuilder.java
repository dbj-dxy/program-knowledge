package cn.dbj.knowledge.design.creation.builder;

/**
 * @author DBJ
 * @date 2020-08-20
 * 具体建造者
 */
public class KeyboardBuilder extends BaseBuilder {
    @Override
    public void buildScreen() {
    }

    @Override
    public void buildKeyboard() {
        product.setKeyboard("机械键盘");
    }

    @Override
    public void buildMouse() {
    }
}
