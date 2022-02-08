package cn.dbj.knowledge.design.structural.decorator;

/**
 * @author DBJ
 * @date 2020-08-24
 */
public class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("具体构建方法");
    }
}
