package cn.dbj.knowledge.design.structural.flyweight.mix;

/**
 * 抽象享元角色
 */
public interface Flyweight {
    void operation(UnsharedConcreteFlyweight state);
}
