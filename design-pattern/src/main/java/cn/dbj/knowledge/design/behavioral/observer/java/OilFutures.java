package cn.dbj.knowledge.design.behavioral.observer.java;

import java.util.Observable;

/**
 * 具体目标类：原油期货
 */
public class OilFutures extends Observable {
    private float price;

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        // 设置内部标志位，注明数据发生变化
        super.setChanged();
        // 通知观察者价格改变了
        super.notifyObservers(price);
        this.price = price;
    }
}
