package cn.dbj.knowledge.design.structural.flyweight.mix;

import java.util.ArrayList;
import java.util.List;

/**
 * 复合享元角色
 */
public class CompositeConcreteFlyweight implements Flyweight {

    private final List<Flyweight> flyweights = new ArrayList<>();

    private void add(Flyweight flyweight) {
        flyweights.add(flyweight);
    }

    private void remove(Flyweight flyweight) {
        flyweights.remove(flyweight);
    }

    private String shardPart;

    public String getShardPart() {
        return "共享信息 -> " + shardPart;
    }

    public void setShardPart(String shardPart) {
        this.shardPart = shardPart;
    }

    public CompositeConcreteFlyweight(String shardPart) {
        this.shardPart = shardPart;
    }

    @Override
    public void operation(UnsharedConcreteFlyweight state) {
        System.out.println(getShardPart());
        System.out.println(state.getUnShardPart());
        flyweights.forEach(i -> i.operation(state));
    }
}
