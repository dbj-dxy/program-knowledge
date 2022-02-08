import cn.dbj.knowledge.design.behavioral.template.Fish;
import cn.dbj.knowledge.design.behavioral.template.Vegetables;
import org.junit.Test;

public class TestTemplateMethod {

    @Test
    public void template() {
        new Fish().templateMethod();
        new Vegetables().templateMethod();
    }
}
