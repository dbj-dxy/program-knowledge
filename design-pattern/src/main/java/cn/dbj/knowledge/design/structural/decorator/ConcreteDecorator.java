package cn.dbj.knowledge.design.structural.decorator;

/**
 * @author DBJ
 * @date 2020-08-24
 */
public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addFunction();
    }

    private void addFunction() {
        System.out.println("增加功能");
    }
}
