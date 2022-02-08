package cn.dbj.knowledge.design.creation.singleton;

/**
 * @author DBJ
 * @date 2020-08-03
 * 线程安全的懒汉单例
 * 优：线程安全；延迟加载；效率较高
 * 推荐
 */
public class SyncLazySingleton {

    private volatile static SyncLazySingleton singleton = null;

    private SyncLazySingleton(){}

    public static SyncLazySingleton getSingleton() {
        if (singleton == null) {
            synchronized (SyncLazySingleton.class) {
                if (singleton == null) {
                    singleton = new SyncLazySingleton();
                }
            }
        }
        return singleton;
    }
}
