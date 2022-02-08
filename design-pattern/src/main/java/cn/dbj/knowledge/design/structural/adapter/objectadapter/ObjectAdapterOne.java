package cn.dbj.knowledge.design.structural.adapter.objectadapter;


import cn.dbj.knowledge.design.structural.adapter.AdapterInterface;
import cn.dbj.knowledge.design.structural.adapter.oldsystem.OldServiceOneImpl;

/**
 * @author DBJ
 * @date 2020-08-20
 * 对象结构适配器, 实例化一个旧系统接口的实现类, 直接调用该实现类的方法, 耦合度低
 */
public class ObjectAdapterOne implements AdapterInterface {

    /**
     * description 不要使用接口引用, 不确定旧系统有多个实现, 避免可能造成不必要的错误
     * @date 2020-08-20 17:49
     */
    private OldServiceOneImpl oldService;

    public ObjectAdapterOne() {
        this.oldService = new OldServiceOneImpl();
    }

    @Override
    public void newJob() {
        oldService.oldJob();
    }
}
