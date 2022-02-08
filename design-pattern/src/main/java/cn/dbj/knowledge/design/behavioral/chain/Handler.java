package cn.dbj.knowledge.design.behavioral.chain;

/**
 * 抽象处理者角色
 */
public abstract class Handler {
    private Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }

    /**
     * 处理请求的方法
     *
     * @param request request
     */
    public abstract void handleRequest(String request);
}
