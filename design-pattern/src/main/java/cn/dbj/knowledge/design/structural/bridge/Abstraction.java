package cn.dbj.knowledge.design.structural.bridge;

/**
 * 抽象化角色
 */
public abstract class Abstraction {

    protected Implementor impl;

    public Abstraction(Implementor impl) {
        this.impl = impl;
    }

    public abstract void operation();
}
