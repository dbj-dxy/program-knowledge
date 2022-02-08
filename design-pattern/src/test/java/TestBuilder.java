import cn.dbj.knowledge.design.creation.builder.*;
import org.junit.Test;

public class TestBuilder {

    @Test
    public void builder() {
        BaseBuilder screenBuilder = new ScreenBuilder();
        BaseBuilder keyboardBuilder = new KeyboardBuilder();
        BaseBuilder mouseBuilder = new MouseBuilder();
        Manager manager1 = new Manager(screenBuilder);
        Manager manager2 = new Manager(keyboardBuilder);
        Manager manager3 = new Manager(mouseBuilder);
        Product product1 = manager1.construct();
        product1.show();
        Product product2 = manager2.construct();
        product2.show();
        Product product3 = manager3.construct();
        product3.show();
    }
}
