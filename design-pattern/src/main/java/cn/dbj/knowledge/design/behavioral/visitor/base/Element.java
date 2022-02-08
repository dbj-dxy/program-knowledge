package cn.dbj.knowledge.design.behavioral.visitor.base;

/**
 * 抽象元素类
 */
public interface Element {
    void accept(Visitor visitor);
}
