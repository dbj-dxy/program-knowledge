package cn.dbj.knowledge.design.behavioral.observer.base;

/**
 * 具体观察者2
 */
public class ConcreteObserver2 implements Observer{
    @Override
    public void response() {
        System.out.println("具体观察者2作出反应！");
    }
}
