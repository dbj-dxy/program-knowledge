import cn.dbj.knowledge.design.behavioral.observer.base.*;
import cn.dbj.knowledge.design.behavioral.observer.java.ConcreteObserver;
import cn.dbj.knowledge.design.behavioral.observer.java.OilFutures;
import org.junit.Test;

public class TestObserver {

    @Test
    public void observer() {
        Observer observer1 = new ConcreteObserver1();
        Observer observer2 = new ConcreteObserver2();
        Subject subject = new ConcreteSubject();

        subject.add(observer1);
        subject.add(observer2);
        subject.notifyObserver();
    }

    @Test
    public void javaObserver() {
        java.util.Observer observer = new ConcreteObserver();
        OilFutures futures = new OilFutures();
        futures.addObserver(observer);
        futures.setPrice(111);
    }

}
