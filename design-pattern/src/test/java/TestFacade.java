import cn.dbj.knowledge.design.structural.facade.Facade;
import org.junit.Test;

public class TestFacade {

    @Test
    public void facade() {
        Facade facade = new Facade();
        facade.method();
    }
}
