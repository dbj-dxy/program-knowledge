package cn.dbj.knowledge.design.behavioral.interpreter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 终结符表达式类
 */
public class TerminalExpression implements AbstractExpression {
    private final Set<String> set = new HashSet<>();

    public TerminalExpression(String[] data) {
        set.addAll(Arrays.asList(data));
    }

    @Override
    public boolean interpret(String info) {
        return set.contains(info);
    }
}
