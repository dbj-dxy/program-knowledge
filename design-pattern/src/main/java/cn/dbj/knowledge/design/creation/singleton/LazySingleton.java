package cn.dbj.knowledge.design.creation.singleton;

/**
 * @author DBJ
 * @date 2020-08-03
 *
 * 懒汉单例
 * 优：懒加载
 * 缺：线程安全
 * 不推荐
 */
public class LazySingleton {

    private static LazySingleton singleton = null;

    private LazySingleton(){}

    public static LazySingleton getSingleton() {
        if (null == singleton) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
