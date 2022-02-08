import cn.dbj.knowledge.design.behavioral.interpreter.Context;
import org.junit.Test;

public class TestInterpreter {

    @Test
    public void interpreter() {
        Context bus = new Context();
        bus.operation("韶关的老人");
        bus.operation("韶关的年轻人");
        bus.operation("广州的妇女");
        bus.operation("广州的儿童");
        bus.operation("山东的儿童");
    }

}
