package cn.dbj.knowledge.design.behavioral.mediator.base;

/**
 * 具体同事类2
 */
public class ConcreteColleague2 extends Colleague {
    @Override
    public void receive() {
        System.out.println("具体同事类2收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类2发出请求。");
        // 请中介者转发
        mediator.relay(this);
    }
}