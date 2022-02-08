package cn.dbj.knowledge.design.structural.bridge;

/**
 * 扩展抽象化角色
 */
public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor impl) {
        super(impl);
    }

    @Override
    public void operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        impl.operationImpl();
    }
}
