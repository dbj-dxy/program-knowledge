package cn.dbj.knowledge.design.behavioral.template;

/**
 * @author DBJ
 * @date 2020-08-24
 */
public class Fish extends BaseTemplate{
    @Override
    void condiment() {
        System.out.println("加酱油");
    }

    @Override
    void food() {
        System.out.println("鲤鱼");
    }

}
