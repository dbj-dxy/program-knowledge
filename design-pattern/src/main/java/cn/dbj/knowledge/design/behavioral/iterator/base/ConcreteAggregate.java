package cn.dbj.knowledge.design.behavioral.iterator.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体聚合
 */
public class ConcreteAggregate implements Aggregate {
    private final List<Object> list = new ArrayList<Object>();

    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    @Override
    public Iterator getIterator() {
        return new ConcreteIterator(list);
    }
}
