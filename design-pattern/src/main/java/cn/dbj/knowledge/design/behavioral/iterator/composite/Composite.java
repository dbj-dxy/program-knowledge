package cn.dbj.knowledge.design.behavioral.iterator.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝构件
 */
public class Composite implements Component {

    private final List<Component> children = new ArrayList<>();

    public void add(Component c) {
        children.add(c);
    }

    public void remove(Component c) {
        children.remove(c);
    }

    public Component getChild(int i) {
        return children.get(i);
    }

    @Override
    public void operation() {
        children.forEach(Component::operation);
    }

    public CIterator getIterator() {
        return new CConcreteIterator(children);
    }
}
