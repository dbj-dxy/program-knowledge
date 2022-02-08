package cn.dbj.knowledge.design.behavioral.iterator.composite;

/**
 * 抽象迭代器
 */
public interface CIterator {
    Component first();
    Component next();
    boolean hasNext();
}
