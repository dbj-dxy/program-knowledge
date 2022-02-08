package cn.dbj.knowledge.design.structural.composite.transparent;

/**
 * 抽象构件
 */
public interface TComponent {
    void add(TComponent c) throws Exception;

    void remove(TComponent c) throws Exception;

    TComponent getChild(int i) throws Exception;

    void operation();
}
