package cn.dbj.knowledge.design.structural.composite.safe;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝构件
 */
public class SComposite implements SComponent {

    private final List<SComponent> children = new ArrayList<>();

    public void add(SComponent c) {
        children.add(c);
    }

    public void remove(SComponent c) {
        children.remove(c);
    }

    public SComponent getChild(int i) {
        return children.get(i);
    }

    @Override
    public void operation() {
        children.forEach(SComponent::operation);
    }
}
