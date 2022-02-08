import cn.dbj.knowledge.design.structural.flyweight.base.Flyweight;
import cn.dbj.knowledge.design.structural.flyweight.base.FlyweightFactory;
import cn.dbj.knowledge.design.structural.flyweight.base.UnsharedConcreteFlyweight;
import org.junit.Test;

public class TestFlyWeight {

    @Test
    public void baseFlyWeight() {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight f01 = factory.getFlyweight("a");
        Flyweight f02 = factory.getFlyweight("a");
        Flyweight f03 = factory.getFlyweight("a");
        Flyweight f11 = factory.getFlyweight("b");
        Flyweight f12 = factory.getFlyweight("b");
        f01.operation(new UnsharedConcreteFlyweight("第1次调用a。"));
        f02.operation(new UnsharedConcreteFlyweight("第2次调用a。"));
        f03.operation(new UnsharedConcreteFlyweight("第3次调用a。"));
        f11.operation(new UnsharedConcreteFlyweight("第1次调用b。"));
        f12.operation(new UnsharedConcreteFlyweight("第2次调用b。"));
    }

    @Test
    public void mixFlyWeight() {

    }

    @Test
    public void pureFlyWeight() {

    }
}
