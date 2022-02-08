package cn.dbj.knowledge.design.behavioral.command.base;

/**
 * 具体命令
 */
public class ConcreteCommand implements Command {
    private Receiver receiver;

    public ConcreteCommand() {
        receiver = new Receiver();
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
