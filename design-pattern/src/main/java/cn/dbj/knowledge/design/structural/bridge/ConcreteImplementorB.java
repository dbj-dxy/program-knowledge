package cn.dbj.knowledge.design.structural.bridge;

/**
 * 具体实现化角色
 */
public class ConcreteImplementorB implements Implementor {

    @Override
    public void operationImpl() {
        System.out.println("具体实现化 B 角色被访问");
    }
}
