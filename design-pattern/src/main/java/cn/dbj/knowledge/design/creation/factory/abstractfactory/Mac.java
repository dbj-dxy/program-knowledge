package cn.dbj.knowledge.design.creation.factory.abstractfactory;

/**
 * @author DBJ
 * @date 2020-08-17
 */
public class Mac implements Computer{

    @Override
    public void sign() {
        System.out.println("This is a Mac");
    }
}
