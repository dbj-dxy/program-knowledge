package cn.dbj.knowledge.design.behavioral.interpreter;

/**
 * 非终结符表达式类
 */
public class NonTerminalExpression implements AbstractExpression {
    private final AbstractExpression city;
    private final AbstractExpression person;

    public NonTerminalExpression(AbstractExpression city, AbstractExpression person) {
        this.city = city;
        this.person = person;
    }


    @Override
    public boolean interpret(String info) {
        String[] s = info.split("的");
        return city.interpret(s[0]) && person.interpret(s[1]);
    }
}