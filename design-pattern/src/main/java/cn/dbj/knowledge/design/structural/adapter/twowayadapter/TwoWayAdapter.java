package cn.dbj.knowledge.design.structural.adapter.twowayadapter;


import cn.dbj.knowledge.design.structural.adapter.AdapterInterface;
import cn.dbj.knowledge.design.structural.adapter.oldsystem.OldService;

/**
 * @author DBJ
 * @date 2020-08-20
 * 双向适配器, 需要实现新系统和旧系统的接口
 */
public class TwoWayAdapter implements AdapterInterface, OldService {

    private AdapterInterface newService;
    private OldService oldService;

    public TwoWayAdapter(AdapterInterface newService) {
        this.newService = newService;
    }

    public TwoWayAdapter(OldService oldService) {
        this.oldService = oldService;
    }

    @Override
    public void newJob() {
        oldService.oldJob();
    }

    @Override
    public void oldJob() {
        newService.newJob();
    }
    
}
