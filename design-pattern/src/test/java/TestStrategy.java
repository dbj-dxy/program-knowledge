import cn.dbj.knowledge.design.behavioral.strategy.*;
import org.junit.Test;

public class TestStrategy {

    @Test
    public void strategy() {
        Context c = new Context();
        Strategy s = new ConcreteStrategyA();
        c.setStrategy(s);
        c.strategyMethod();
        System.out.println("-----------------");
        s = new ConcreteStrategyB();
        c.setStrategy(s);
        c.strategyMethod();
    }

    @Test
    public void strategyFactory() {
        StrategyFactory factory = new StrategyFactory();

        factory.put("a", new ConcreteStrategyA());
        factory.put("b", new ConcreteStrategyB());

        factory.strategyMethod("a");
    }

}
