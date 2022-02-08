package cn.dbj.knowledge.design.behavioral.state;

/**
 * 环境类
 */
public class Context {
    private AbstractState state;

    public Context() {
        this.state = new Normal();
    }

    public void setState(AbstractState state) {
        this.state = state;
    }

    public AbstractState getState() {
        return state;
    }

    public void handle() {
        state.action(this);
    }
}
