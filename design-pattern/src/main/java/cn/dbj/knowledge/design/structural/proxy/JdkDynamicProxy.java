package cn.dbj.knowledge.design.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author DBJ
 * @date 2020-08-20
 * 演示 jdk 动态代理, 两种方式
 * 1-类实现 InvocationHandler
 * 2-匿名内部类实现 InvocationHandler
 * <a href="https://www.cnblogs.com/zuidongfeng/p/8735241.html">JDK动态代理</a>
 */
public class JdkDynamicProxy<T> implements InvocationHandler {

    private final T target;

    public JdkDynamicProxy(T target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("before method");
    }

    private void after() {
        System.out.println("after method");
    }
}
