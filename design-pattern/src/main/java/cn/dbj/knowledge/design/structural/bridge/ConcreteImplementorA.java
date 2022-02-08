package cn.dbj.knowledge.design.structural.bridge;

/**
 * 具体实现化角色
 */
public class ConcreteImplementorA implements Implementor {

    @Override
    public void operationImpl() {
        System.out.println("具体实现化 A 角色被访问");
    }
}
