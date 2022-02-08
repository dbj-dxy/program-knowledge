package cn.dbj.knowledge.design.behavioral.state;

/**
 * 超时状态
 */
public class Timeout extends AbstractState{

    @Override
    public void action(Context c) {
        System.out.println("超时状态");
        c.setState(new Normal());
    }
}
