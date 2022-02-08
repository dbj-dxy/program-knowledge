package cn.dbj.knowledge.design.structural.proxy;

/**
 * @author DBJ
 * @date 2020-08-20
 */
public class Target implements BaseTarget {
    @Override
    public void doJob() {
        System.out.println("I`m real target");
    }

    public final void finalJob() {
        System.out.println("finalJob");
    }
}
