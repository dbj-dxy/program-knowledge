package cn.dbj.knowledge.design.behavioral.visitor.base;

/**
 * 抽象访问者
 */
public interface Visitor {
    void visit(ConcreteElementA element);

    void visit(ConcreteElementB element);
}
