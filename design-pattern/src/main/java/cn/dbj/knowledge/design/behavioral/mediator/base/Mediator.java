package cn.dbj.knowledge.design.behavioral.mediator.base;

/**
 * 抽象中介者
 */
public abstract class Mediator {
    public abstract void register(Colleague colleague);

    /**
     * 转发
     */
    public abstract void relay(Colleague cl);
}
