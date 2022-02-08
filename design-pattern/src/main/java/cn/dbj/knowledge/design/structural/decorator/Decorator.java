package cn.dbj.knowledge.design.structural.decorator;

/**
 * @author DBJ
 * @date 2020-08-24
 */
public class Decorator implements Component{

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
