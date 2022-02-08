package cn.dbj.knowledge.design.behavioral.iterator.base;

/**
 * 抽象迭代器
 */
public interface Iterator {
    Object first();
    Object next();
    boolean hasNext();
}
