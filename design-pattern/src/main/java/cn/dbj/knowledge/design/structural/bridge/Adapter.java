package cn.dbj.knowledge.design.structural.bridge;

/**
 * 适配器
 */
public class Adapter extends ConcreteImplementorAdapter {

    private final Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void operationImpl() {
        super.operationImpl();
        adaptee.job();
    }
}
