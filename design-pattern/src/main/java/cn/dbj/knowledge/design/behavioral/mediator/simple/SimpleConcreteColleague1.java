package cn.dbj.knowledge.design.behavioral.mediator.simple;

/**
 * 具体同事类1
 */
public class SimpleConcreteColleague1 implements SimpleColleague {
    public SimpleConcreteColleague1() {
        SimpleMediator smd = SimpleMediator.getMedium();
        smd.register(this);
    }

    @Override
    public void receive() {
        System.out.println("具体同事类1：收到请求。");
    }

    @Override
    public void send() {
        SimpleMediator smd = SimpleMediator.getMedium();
        System.out.println("具体同事类1：发出请求...");
        //请中介者转发
        smd.relay(this);
    }
}
