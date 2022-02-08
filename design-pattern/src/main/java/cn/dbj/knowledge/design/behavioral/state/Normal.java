package cn.dbj.knowledge.design.behavioral.state;

/**
 * 正常状态
 */
public class Normal extends AbstractState{

    @Override
    public void action(Context c) {
        System.out.println("正常状态");
        c.setState(new Fail());
    }
}
