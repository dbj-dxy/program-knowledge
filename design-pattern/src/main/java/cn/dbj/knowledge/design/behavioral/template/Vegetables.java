package cn.dbj.knowledge.design.behavioral.template;

/**
 * @author DBJ
 * @date 2020-08-24
 */
public class Vegetables extends BaseTemplate{
    @Override
    void condiment() {
        System.out.println("加盐");
    }

    @Override
    void food() {
        System.out.println("蒜泥白菜");
    }

    @Override
    public boolean needParsley() {
        return false;
    }

}
