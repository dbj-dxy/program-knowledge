package cn.dbj.knowledge.design.creation.factory.simplefactory;

/**
 * @author DBJ
 * @date 2020-08-17
 */
public class IPhone implements Phone{

    private String phoneId;

    public IPhone(String phoneId) {
        this.phoneId = phoneId;
    }

    @Override
    public void call() {
        System.out.println("I`m iPhone, NO." + phoneId);
        System.out.println("I can call someBody");
    }

    @Override
    public void message() {
        System.out.println("I`m iPhone, NO." + phoneId);
        System.out.println("I can send message to someBody");
    }
}
