import cn.dbj.knowledge.design.behavioral.iterator.base.Aggregate;
import cn.dbj.knowledge.design.behavioral.iterator.base.ConcreteAggregate;
import cn.dbj.knowledge.design.behavioral.iterator.base.Iterator;
import cn.dbj.knowledge.design.behavioral.iterator.composite.CIterator;
import cn.dbj.knowledge.design.behavioral.iterator.composite.Component;
import cn.dbj.knowledge.design.behavioral.iterator.composite.Composite;
import cn.dbj.knowledge.design.behavioral.iterator.composite.Leaf;
import org.junit.Test;

public class TestIterator {

    @Test
    public void iterator() {
        Aggregate ag = new ConcreteAggregate();
        ag.add("中山大学");
        ag.add("华南理工");
        ag.add("韶关学院");
        System.out.print("聚合的内容有：");
        Iterator it = ag.getIterator();
        while (it.hasNext()) {
            Object ob = it.next();
            System.out.print(ob.toString() + "\t");
        }
        Object ob = it.first();
        System.out.println("\nFirst：" + ob.toString());
    }

    @Test
    public void compositeIterator() {
        Component a = new Leaf("a");
        Component b = new Leaf("b");
        Component c = new Leaf("c");
        Composite composite = new Composite();
        composite.add(a);
        composite.add(b);
        composite.add(c);
        composite.operation();
        CIterator iterator = composite.getIterator();
        System.out.println(iterator.first());
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}
