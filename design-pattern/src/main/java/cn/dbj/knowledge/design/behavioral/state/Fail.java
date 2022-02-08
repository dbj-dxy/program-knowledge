package cn.dbj.knowledge.design.behavioral.state;

/**
 * 失败状态
 */
public class Fail extends AbstractState{
    @Override
    public void action(Context c) {
        System.out.println("失败状态");
        c.setState(new Timeout());
    }
}
