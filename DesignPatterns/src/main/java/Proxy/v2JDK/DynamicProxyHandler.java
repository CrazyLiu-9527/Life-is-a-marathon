package Proxy.v2JDK;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhiyuanliu
 * @date 2020/5/22 15:48
 */
public class DynamicProxyHandler implements InvocationHandler {
    private Object object;

    public DynamicProxyHandler(Object object) {
        this.object = object;
    }

    /**
     * @param proxy  生成的代理对象
     * @param method 调用的方法
     * @param args   方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买房前准备");
        Object result = method.invoke(object, args);
        System.out.println("买房后装修");
        return result;
    }
}
