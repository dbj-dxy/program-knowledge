package cn.dbj.knowledge.design.behavioral.visitor.composite;

import cn.dbj.knowledge.design.behavioral.visitor.base.ConcreteElementA;
import cn.dbj.knowledge.design.behavioral.visitor.base.ConcreteElementB;
import cn.dbj.knowledge.design.behavioral.visitor.base.Element;
import cn.dbj.knowledge.design.behavioral.visitor.base.Visitor;

import java.util.ArrayList;
import java.util.List;

public class CompositeElement implements Element {

    private final List<Element> leaves = new ArrayList<>();

    public void add(Element e) {
        leaves.add(e);
    }

    public void remove(Element e) {
        leaves.remove(e);
    }

    public Element getChild(int i) {
        return leaves.get(i);
    }

    @Override
    public void accept(Visitor visitor) {
        leaves.forEach(i -> {
            if (i instanceof ConcreteElementA) {
                visitor.visit((ConcreteElementA) i);
            } else if (i instanceof ConcreteElementB) {
                visitor.visit((ConcreteElementB) i);
            }
        });
    }
}
