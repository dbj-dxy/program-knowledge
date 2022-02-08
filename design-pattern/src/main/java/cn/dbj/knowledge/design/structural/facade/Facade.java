package cn.dbj.knowledge.design.structural.facade;

/**
 * 统一外观接口
 */
public class Facade {

    SubSystemA a = new SubSystemA();
    SubSystemB b = new SubSystemB();

    public void method() {
        a.method();
        b.method();
    }
}
