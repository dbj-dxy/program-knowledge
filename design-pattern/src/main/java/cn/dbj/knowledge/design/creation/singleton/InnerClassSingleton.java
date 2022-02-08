package cn.dbj.knowledge.design.creation.singleton;

/**
 * @author DBJ
 * @date 2020-08-03
 * 内部类实现单例
 * 优：懒加载，InnerClassSingleton加载后没有生成实例，调用getSingleton 方法后才会加载SingletonHolder
 */
public class InnerClassSingleton {
    private static class SingletonHolder {
        private static final InnerClassSingleton SINGLETON = new InnerClassSingleton();
    }

    private InnerClassSingleton() {}

    public static InnerClassSingleton getSingleton() {
        return SingletonHolder.SINGLETON;
    }
}
