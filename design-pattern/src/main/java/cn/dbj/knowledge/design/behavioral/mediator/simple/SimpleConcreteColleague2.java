package cn.dbj.knowledge.design.behavioral.mediator.simple;

/**
 * 具体同事类2
 */
public class SimpleConcreteColleague2 implements SimpleColleague {
    public SimpleConcreteColleague2() {
        SimpleMediator smd = SimpleMediator.getMedium();
        smd.register(this);
    }

    @Override
    public void receive() {
        System.out.println("具体同事类2：收到请求。");
    }

    @Override
    public void send() {
        SimpleMediator smd = SimpleMediator.getMedium();
        System.out.println("具体同事类2：发出请求...");
        //请中介者转发
        smd.relay(this);
    }
}
