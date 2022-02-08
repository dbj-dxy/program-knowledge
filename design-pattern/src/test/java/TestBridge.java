import cn.dbj.knowledge.design.structural.bridge.Abstraction;
import cn.dbj.knowledge.design.structural.bridge.ConcreteImplementorA;
import cn.dbj.knowledge.design.structural.bridge.Implementor;
import cn.dbj.knowledge.design.structural.bridge.RefinedAbstraction;
import org.junit.Test;

public class TestBridge {

    @Test
    public void bridge() {
        Implementor impl = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(impl);
        abs.operation();
    }

}
