package cn.dbj.knowledge.design.creation.factory.abstractfactory;

import cn.dbj.knowledge.design.creation.factory.simplefactory.Phone;

/**
 * @author DBJ
 * @date 2020-08-17
 */
public interface AbstractFactory {
    Phone makePhone();
    Computer makeComputer();
}
