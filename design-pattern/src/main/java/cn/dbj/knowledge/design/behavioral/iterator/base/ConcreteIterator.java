package cn.dbj.knowledge.design.behavioral.iterator.base;

import java.util.List;

/**
 * 具体迭代器
 */
public class ConcreteIterator implements Iterator {

    private final List<Object> list;
    private int index = -1;

    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object first() {
        return list.get(0);
    }

    @Override
    public Object next() {
        return hasNext() ? list.get(++index) : null;
    }

    @Override
    public boolean hasNext() {
        return index < list.size() - 1;
    }
}
