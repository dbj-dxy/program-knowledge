package cn.dbj.knowledge.design.creation.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author DBJ
 * @date 2020-08-03
 * 通过CAS保证线程安全
 * 优：无需加锁，不阻塞
 * 缺：长时间自旋不成功，CPU开销大
 * JDK1.6以后默认开启了自旋（--XX:+UseSpinning），可以通过JVM设置CAS的自旋操作次数来解决（-XX:PreBlockSpin=10，JVM的默认自旋次数是10），当超过指定次数后，自动失败退出。还有一种自适应自旋锁，自旋的时间不再固定，会根据前一次同一个锁上的自旋时间以及锁的拥有者的状态来决定的。
 * CAS的功能的局限性，CAS只能保证单个内存中的值的原子性，在java中原子性不一定能保证线程安全，还需要volatile保证有序性来实现线程安全。在需要保证多个内存中的值的情况下，CAS也无能为力，可以看情况使用悲观锁。所以说在并发冲突概率比较高的环境中，尽量不要使用CAS。
 * 其次CAS的核心是依靠可以直接调用底层资源的Unsafe类的CompareAndSwap()方法实现的，在java使用只能使用Atomic包下的相关类，局限性比较大。
 */
public class CasSingleton {

    private final static AtomicReference<CasSingleton> SINGLETON = new AtomicReference<>();

    private CasSingleton() {}

    public static CasSingleton getSingleton() {
        while (true) {
            CasSingleton singleton = SINGLETON.get();
            if (null != singleton) {
                return singleton;
            }

            singleton = new CasSingleton();
            if (SINGLETON.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}
