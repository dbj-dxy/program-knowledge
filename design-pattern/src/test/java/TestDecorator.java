import cn.dbj.knowledge.design.structural.decorator.Component;
import cn.dbj.knowledge.design.structural.decorator.ConcreteComponent;
import cn.dbj.knowledge.design.structural.decorator.ConcreteDecorator;
import org.junit.Test;

public class TestDecorator {

    @Test
    public void decorator() {
        Component component = new ConcreteComponent();
        Component decorator = new ConcreteDecorator(component);
        decorator.operation();
    }
}
