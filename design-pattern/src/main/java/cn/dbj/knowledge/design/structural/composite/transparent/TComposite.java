package cn.dbj.knowledge.design.structural.composite.transparent;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝构件
 */
public class TComposite implements TComponent {

    private final List<TComponent> children = new ArrayList<>();

    @Override
    public void add(TComponent c) {
        children.add(c);
    }

    @Override
    public void remove(TComponent c) {
        children.remove(c);
    }

    @Override
    public TComponent getChild(int i) {
        return children.get(i);
    }

    @Override
    public void operation() {
        children.forEach(TComponent::operation);
    }
}
