package com.kuang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LiveHandler <T> implements InvocationHandler {

    private Object target;

    public LiveHandler(Object target) {
        this.target = target;
    }

    public Object getInstance() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        prepareGoods();
        method.invoke(target, args);
        pay();
        return null;
    }

    private void prepareGoods() {
        System.out.println("准备商品。。。。。。");
    }

    private void pay() {
        System.out.println("支付。。。。。。");
    }
}
