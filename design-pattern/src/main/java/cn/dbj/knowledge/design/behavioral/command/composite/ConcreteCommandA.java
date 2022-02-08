package cn.dbj.knowledge.design.behavioral.command.composite;

/**
 * 树叶构件: 具体命令A
 */
public class ConcreteCommandA implements AbstractCommand {
    private CompositeReceiver receiver;

    public ConcreteCommandA() {
        receiver = new CompositeReceiver();
    }

    @Override
    public void execute() {
        receiver.action1();
    }
}
