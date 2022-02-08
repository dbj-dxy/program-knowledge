package cn.dbj.knowledge.design.behavioral.memento.prototype;

/**
 * 原型管理者
 */
public class PrototypeCaretaker {
    private OriginatorPrototype opt;
    public void setMemento(OriginatorPrototype opt) {
        this.opt = opt;
    }
    public OriginatorPrototype getMemento() {
        return opt;
    }
}
