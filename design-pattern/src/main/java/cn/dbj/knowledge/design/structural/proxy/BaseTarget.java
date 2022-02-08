package cn.dbj.knowledge.design.structural.proxy;

/**
 * @author DBJ
 * @date 2020-08-20
 * 抽象被代理主题, 不推荐这种代理方式, 推荐 spring-aop / aspectj
 * <a href="https://www.cnblogs.com/Irving/p/9739889.html">spring-aop 和 aspectj 的区别</a>
 */
public interface BaseTarget {

    void doJob();

    default void defaultJob() {
        System.out.println("defaultJob");
    }
}
