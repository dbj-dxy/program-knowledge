package cn.dbj.knowledge.design.structural.adapter.classadapter;


import cn.dbj.knowledge.design.structural.adapter.AdapterInterface;
import cn.dbj.knowledge.design.structural.adapter.oldsystem.OldServiceOneImpl;

/**
 * @author DBJ
 * @date 2020-08-20
 * 类结构适配器, 继承旧系统接口实现类, 耦合度高, 要求对原有系统的内部结构 (不推荐)
 */
public class ClassAdapter extends OldServiceOneImpl implements AdapterInterface {
    @Override
    public void newJob() {
        super.oldJob();
    }
}
