package cn.dbj.knowledge.design.structural.composite.safe;

/**
 * 树叶构件
 */
public class SLeaf implements SComponent {

    private final String name;

    public SLeaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("树叶节点" + name + "被访问");
    }
}
