import cn.dbj.knowledge.design.behavioral.mediator.base.*;
import cn.dbj.knowledge.design.behavioral.mediator.simple.SimpleColleague;
import cn.dbj.knowledge.design.behavioral.mediator.simple.SimpleConcreteColleague1;
import cn.dbj.knowledge.design.behavioral.mediator.simple.SimpleConcreteColleague2;
import org.junit.Test;

public class TestMediator {

    @Test
    public void mediator() {
        Mediator md = new ConcreteMediator();
        Colleague c1, c2;
        c1 = new ConcreteColleague1();
        c2 = new ConcreteColleague2();
        md.register(c1);
        md.register(c2);
        c1.send();
        System.out.println("-------------");
        c2.send();
    }

    @Test
    public void simpleMediator() {
        SimpleColleague c1, c2;
        c1 = new SimpleConcreteColleague1();
        c2 = new SimpleConcreteColleague2();
        c1.send();
        System.out.println("-----------------");
        c2.send();
    }

}
