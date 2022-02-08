package cn.dbj.knowledge.design.structural.composite.transparent;

/**
 * 树叶构件
 */
public class TLeaf implements TComponent {

    private final String name;

    public TLeaf(String name) {
        this.name = name;
    }

    @Override
    public void add(TComponent c) throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    @Override
    public void remove(TComponent c) throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    @Override
    public TComponent getChild(int i) throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    @Override
    public void operation() {
        System.out.println("树叶节点" + name + "被访问");
    }
}
