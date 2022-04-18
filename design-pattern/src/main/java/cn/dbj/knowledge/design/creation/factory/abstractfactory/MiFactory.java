package cn.dbj.knowledge.design.creation.factory.abstractfactory;

import cn.dbj.knowledge.design.creation.factory.simplefactory.MiPhone;
import cn.dbj.knowledge.design.creation.factory.simplefactory.Phone;

/**
 * @author DBJ
 * @date 2020-08-17
 */
public class MiFactory implements AbstractFactory{
    @Override
    public Phone makePhone() {
        return new MiPhone("How are you! I`m fine, and you?");
    }

    @Override
    public Computer makeComputer() {
        return new MiPc();
    }
}
