import cn.dbj.knowledge.design.structural.bridge.Abstraction;
import cn.dbj.knowledge.design.structural.bridge.ConcreteImplementorA;
import cn.dbj.knowledge.design.structural.bridge.Implementor;
import cn.dbj.knowledge.design.structural.bridge.RefinedAbstraction;
import cn.dbj.knowledge.design.structural.composite.safe.SComponent;
import cn.dbj.knowledge.design.structural.composite.safe.SComposite;
import cn.dbj.knowledge.design.structural.composite.safe.SLeaf;
import cn.dbj.knowledge.design.structural.composite.transparent.TComponent;
import cn.dbj.knowledge.design.structural.composite.transparent.TComposite;
import cn.dbj.knowledge.design.structural.composite.transparent.TLeaf;
import org.junit.Test;

public class TestComposite {

    @Test
    public void transparentComposite() throws Exception {
        TComponent root = new TComposite();
        TComponent branch = new TComposite();
        TComponent leafA = new TLeaf("leafA");
        TComponent leafB = new TLeaf("leafB");
        TComponent leafC = new TLeaf("leafC");
        root.add(leafA);
        root.add(branch);

        branch.add(leafB);
        branch.add(leafC);

        root.operation();
    }

    @Test
    public void safeComposite() {
        SComposite root = new SComposite();
        SComposite branch = new SComposite();
        SComponent leafA = new SLeaf("leafA");
        SComponent leafB = new SLeaf("leafB");
        SComponent leafC = new SLeaf("leafC");
        root.add(leafA);
        root.add(branch);

        branch.add(leafB);
        branch.add(leafC);

        root.operation();
    }


    @Test
    public void testComponentComposite() {
        Implementor impl = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(impl);
        abs.operation();
    }
}
