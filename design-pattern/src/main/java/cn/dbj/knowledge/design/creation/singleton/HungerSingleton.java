package cn.dbj.knowledge.design.creation.singleton;

/**
 * @author DBJ
 * @date 2020-08-03
 * 饿汉单例
 * 优：简单，避免实例化时线程安全
 * 缺：内存浪费
 * 不推荐
 */
public class HungerSingleton {

    private static final HungerSingleton SINGLETON = new HungerSingleton();

    private HungerSingleton() {}

    public static HungerSingleton getSingleton () {
        return SINGLETON;
    }
}
