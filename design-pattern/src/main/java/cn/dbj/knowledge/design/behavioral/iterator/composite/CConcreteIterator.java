package cn.dbj.knowledge.design.behavioral.iterator.composite;

import java.util.List;

/**
 * 具体迭代器
 */
public class CConcreteIterator implements CIterator {

    private final List<Component> list;
    private int index = -1;

    public CConcreteIterator(List<Component> list) {
        this.list = list;
    }

    @Override
    public Component first() {
        return list.get(0);
    }

    @Override
    public Component next() {
        return hasNext() ? list.get(++index) : null;
    }

    @Override
    public boolean hasNext() {
        return index < list.size() - 1;
    }
}
