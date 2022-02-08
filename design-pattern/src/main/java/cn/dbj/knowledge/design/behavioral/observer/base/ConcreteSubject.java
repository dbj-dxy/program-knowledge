package cn.dbj.knowledge.design.behavioral.observer.base;

/**
 * 具体目标
 */
public class ConcreteSubject extends Subject {
    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生改变...");
        System.out.println("--------------");
        observers.forEach(Observer::response);
    }
}
