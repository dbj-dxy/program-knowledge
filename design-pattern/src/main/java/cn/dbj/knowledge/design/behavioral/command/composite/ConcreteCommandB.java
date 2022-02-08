package cn.dbj.knowledge.design.behavioral.command.composite;

/**
 * 树叶构件: 具体命令B
 */
public class ConcreteCommandB implements AbstractCommand {
    private CompositeReceiver receiver;

    public ConcreteCommandB() {
        receiver = new CompositeReceiver();
    }

    @Override
    public void execute() {
        receiver.action2();
    }
}
