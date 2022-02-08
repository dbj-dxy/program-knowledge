package cn.dbj.knowledge.design.structural.proxy;

/**
 * @author DBJ
 * @date 2020-08-20
 */
public class Proxy implements BaseTarget {

    private final BaseTarget target;

    public Proxy(BaseTarget target) {
        this.target = target;
    }

    private void before() {
        System.out.println("before the job");
    }

    private void after() {
        System.out.println("after the job");
    }

    @Override
    public void doJob() {
        before();
        target.doJob();
        after();
    }
}
