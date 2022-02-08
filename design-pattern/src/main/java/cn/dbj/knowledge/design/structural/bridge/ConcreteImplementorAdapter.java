package cn.dbj.knowledge.design.structural.bridge;

/**
 * 具体实现化角色
 */
public class ConcreteImplementorAdapter implements Implementor {

    @Override
    public void operationImpl() {
        System.out.println("具体实现化 B 角色被访问");
    }
}
