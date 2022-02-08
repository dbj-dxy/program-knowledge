import cn.dbj.knowledge.design.behavioral.visitor.base.*;
import org.junit.Test;

public class TestVisitor {

    @Test
    public void visitor() {
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor = new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor = new ConcreteVisitorB();
        os.accept(visitor);
    }
}
