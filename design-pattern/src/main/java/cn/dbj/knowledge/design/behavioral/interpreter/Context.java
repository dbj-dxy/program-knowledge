package cn.dbj.knowledge.design.behavioral.interpreter;

/**
 * 环境类
 */
public class Context {
    private final String[] cities = {"韶关", "广州"};
    private final String[] persons = {"老人", "妇女", "儿童"};

    private final AbstractExpression cityPerson;

    public Context() {
        // 数据初始化
        AbstractExpression city = new TerminalExpression(cities);
        AbstractExpression person = new TerminalExpression(persons);
        cityPerson = new NonTerminalExpression(city, person);
    }

    public void operation(String info) {
        //调用相关表达式类的解释方法
        if (cityPerson.interpret(info)) {
            System.out.println("您是" + info + "，您本次乘车免费！");
        } else {
            System.out.println(info + "，您不是免费人员，本次乘车扣费2元！");
        }
    }
}
