import cn.dbj.knowledge.design.creation.factory.abstractfactory.AbstractFactory;
import cn.dbj.knowledge.design.creation.factory.abstractfactory.AppleFactory;
import cn.dbj.knowledge.design.creation.factory.abstractfactory.Computer;
import cn.dbj.knowledge.design.creation.factory.abstractfactory.MiFactory;
import cn.dbj.knowledge.design.creation.factory.simplefactory.Phone;
import org.junit.Test;

public class TestAbstractFactory {

    @Test
    public void buyIPhone() {
        AbstractFactory factory = new AppleFactory();
        Phone phone = factory.makePhone();
        phone.call();
        phone.message();
    }

    @Test
    public void buyMiPhone() {
        AbstractFactory factory = new MiFactory();
        Phone phone = factory.makePhone();
        phone.call();
        phone.message();
    }

    @Test
    public void buyMac() {
        AbstractFactory factory = new AppleFactory();
        Computer pc = factory.makeComputer();
        pc.sign();
    }

    @Test
    public void buyMiPc() {
        AbstractFactory factory = new MiFactory();
        Computer pc = factory.makeComputer();
        pc.sign();
    }
}
