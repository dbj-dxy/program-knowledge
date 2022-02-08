import cn.dbj.knowledge.design.creation.factory.AbstractFactory;
import cn.dbj.knowledge.design.creation.factory.IPhoneFactory;
import cn.dbj.knowledge.design.creation.factory.MiPhoneFactory;
import cn.dbj.knowledge.design.creation.factory.simplefactory.Phone;
import org.junit.Test;

public class TestFactory {

    @Test
    public void buyIPhone() {
        AbstractFactory factory = new IPhoneFactory();
        Phone phone = factory.makePhone();
        phone.call();
        phone.message();
    }

    @Test
    public void buyMiPhone() {
        AbstractFactory factory = new MiPhoneFactory();
        Phone phone = factory.makePhone();
        phone.call();
        phone.message();
    }
}
