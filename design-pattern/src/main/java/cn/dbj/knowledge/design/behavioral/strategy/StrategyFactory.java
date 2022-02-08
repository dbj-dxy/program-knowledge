package cn.dbj.knowledge.design.behavioral.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂
 */
public class StrategyFactory {
    private final Map<String, Strategy> factory = new HashMap<>();

    public void put(String key, Strategy s) {
        factory.put(key, s);
    }

    public Strategy get(String key) {
        return factory.get(key);
    }

    public void strategyMethod(String key) {
        factory.get(key).strategyMethod();
    }
}
