package cn.dbj.knowledge.design.behavioral.interpreter;

/**
 * 抽象表达式类
 */
public interface AbstractExpression {
    /**
     * 解释方法
     * @param info info
     */
    boolean interpret(String info);
}
