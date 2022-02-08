import cn.dbj.knowledge.design.behavioral.command.base.Command;
import cn.dbj.knowledge.design.behavioral.command.base.ConcreteCommand;
import cn.dbj.knowledge.design.behavioral.command.base.Invoker;
import cn.dbj.knowledge.design.behavioral.command.composite.AbstractCommand;
import cn.dbj.knowledge.design.behavioral.command.composite.CompositeInvoker;
import cn.dbj.knowledge.design.behavioral.command.composite.ConcreteCommandA;
import cn.dbj.knowledge.design.behavioral.command.composite.ConcreteCommandB;
import org.junit.Test;

public class TestCommand {

    @Test
    public void command() {
        Command command = new ConcreteCommand();
        Invoker invoker = new Invoker(command);
        invoker.call();
    }

    @Test
    public void compositeCommand() {
        AbstractCommand commandA = new ConcreteCommandA();
        AbstractCommand commandB = new ConcreteCommandB();
        CompositeInvoker invoker = new CompositeInvoker();
        invoker.add(commandA);
        invoker.add(commandB);
        invoker.execute();
    }

}
