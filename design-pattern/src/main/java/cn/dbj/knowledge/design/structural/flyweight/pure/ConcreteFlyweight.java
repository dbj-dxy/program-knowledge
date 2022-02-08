package cn.dbj.knowledge.design.structural.flyweight.pure;

/**
 * 具体享元角色
 */
public class ConcreteFlyweight implements Flyweight {

    private String shardPart;

    public String getShardPart() {
        return "共享信息 -> " + shardPart;
    }

    public void setShardPart(String shardPart) {
        this.shardPart = shardPart;
    }

    public ConcreteFlyweight(String shardPart) {
        this.shardPart = shardPart;
    }

    @Override
    public void operation() {
        System.out.println(getShardPart());
    }
}
