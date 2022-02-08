package cn.dbj.knowledge.design.creation.builder;

/**
 * @author DBJ
 * @date 2020-08-20
 * 具体建造者
 */
public class ScreenBuilder extends BaseBuilder {
    @Override
    public void buildScreen() {
        product.setScreen("IPS屏幕");
    }

    @Override
    public void buildKeyboard() {
    }

    @Override
    public void buildMouse() {
    }
}
