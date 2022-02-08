package cn.dbj.knowledge.design.behavioral.observer.java;


import java.util.Observable;
import java.util.Observer;

/**
 * 具体观察者
 */
public class ConcreteObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o);
        System.out.println(arg);
        System.out.println("具体观察者1作出反应！");
    }
}
