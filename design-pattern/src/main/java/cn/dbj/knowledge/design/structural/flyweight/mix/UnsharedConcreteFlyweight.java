package cn.dbj.knowledge.design.structural.flyweight.mix;

/**
 * 非享元角色
 */
public class UnsharedConcreteFlyweight {

    private String unShardPart;

    public UnsharedConcreteFlyweight(String unShardPart) {
        this.unShardPart = unShardPart;
    }

    public String getUnShardPart() {
        return "非共享信息 -> " + unShardPart;
    }

    public void setUnShardPart(String unShardPart) {
        this.unShardPart = unShardPart;
    }
}
