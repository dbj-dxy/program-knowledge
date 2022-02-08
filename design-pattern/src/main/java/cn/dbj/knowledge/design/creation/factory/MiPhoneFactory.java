package cn.dbj.knowledge.design.creation.factory;

import cn.dbj.knowledge.design.creation.factory.simplefactory.MiPhone;
import cn.dbj.knowledge.design.creation.factory.simplefactory.Phone;

/**
 * @author DBJ
 * @date 2020-08-17
 */
public class MiPhoneFactory implements AbstractFactory{

    private int phoneIndex;

    @Override
    public Phone makePhone() {
        return new MiPhone("MiPhone_" + (++phoneIndex));
    }
}
