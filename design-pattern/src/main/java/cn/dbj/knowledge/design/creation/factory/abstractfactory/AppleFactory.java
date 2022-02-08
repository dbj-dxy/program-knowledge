package cn.dbj.knowledge.design.creation.factory.abstractfactory;

import cn.d.designpattern.creationmode.factory.simplefactory.IPhone;
import cn.d.designpattern.creationmode.factory.simplefactory.Phone;

/**
 * @author DBJ
 * @date 2020-08-17
 */
public class AppleFactory implements AbstractFactory{
    @Override
    public Phone makePhone() {
        return new IPhone("NewIphone");
    }

    @Override
    public Computer makeComputer() {
        return new Mac();
    }
}
