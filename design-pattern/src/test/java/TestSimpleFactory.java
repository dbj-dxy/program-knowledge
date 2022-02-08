import cn.dbj.knowledge.design.creation.factory.simplefactory.Phone;
import cn.dbj.knowledge.design.creation.factory.simplefactory.SimplePhoneFactory;
import org.junit.Test;

public class TestSimpleFactory {

    @Test
    public void buyIPhone() {
        SimplePhoneFactory factory = new SimplePhoneFactory();
        Phone phone = factory.makePhone("IPhone");
        phone.call();
        phone.message();
    }

    @Test
    public void buyMiPhone() {
        SimplePhoneFactory factory = new SimplePhoneFactory();
        Phone phone = factory.makePhone("MiPhone");
        phone.call();
        phone.message();
    }
}
