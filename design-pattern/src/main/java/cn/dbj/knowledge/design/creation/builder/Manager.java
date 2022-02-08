package cn.dbj.knowledge.design.creation.builder;

/**
 * @author DBJ
 * @date 2020-08-20
 * 项目建立
 */
public class Manager {
    private BaseBuilder builder;

    public Manager(BaseBuilder builder) {
        this.builder = builder;
    }

    public Product construct() {
        builder.buildScreen();
        builder.buildKeyboard();
        builder.buildMouse();
        return builder.getResult();
    }
}
