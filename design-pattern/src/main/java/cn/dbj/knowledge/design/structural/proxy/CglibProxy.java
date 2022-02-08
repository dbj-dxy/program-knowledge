package cn.dbj.knowledge.design.structural.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author DBJ
 * @date 2020-08-20
 * 依赖 cglib 包实现
 * 需要实现 MethodInterceptor
 * <a href="https://www.cnblogs.com/shijiaqi1066/p/3429691.html">CGLIB学习笔记</a>
 * <a href="https://www.cnblogs.com/wyq1995/p/10945034.html">CGLIB动态代理</a>
 */
public class CglibProxy<T> implements MethodInterceptor {

    private final T target;

    public CglibProxy(T target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public T getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
//        method.invoke(target, args);
//        methodProxy.invoke(target, args);
        Object result = methodProxy.invokeSuper(target, args);
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
