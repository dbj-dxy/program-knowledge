package cn.dbj.knowledge.design.behavioral.state;

import java.util.HashMap;
import java.util.Map;

public class ShareContext extends Context {

    private final Map<String, AbstractState> states = new HashMap<>();

    public ShareContext() {
        super();
        states.put("normal", getState());
    }

    public AbstractState getState(String name) {
        return states.get(name);
    }
}
