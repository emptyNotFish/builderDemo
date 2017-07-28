package com.springboot.test.builderDemo.muti;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxy implements InvocationHandler {
	/**
	 * 维护一个要代理的对象
	 */
	private Object targert;

	public JdkDynamicProxy(Object targert) {
		this.targert = targert;
	}

	@Override
	public Object invoke(Object object, Method method, Object[] arg) throws Throwable {
		System.out.println("代理方法前执行");
		//调用被代理对象的方法
		method.invoke(targert, arg);
		System.out.println("代理方法后执行");
		return null;
	}
	/**
	 * 给目标对象生成代理对象 并返回
	 * @return
	 */
	public Object getProxyInstance(){
		return Proxy.newProxyInstance(targert.getClass().getClassLoader(), targert.getClass().getInterfaces(), this);
	}
	
	public static void main(String[] args) {
		RealSubject subject = new RealSubject();
		Subject proxy = (Subject) (new JdkDynamicProxy(subject).getProxyInstance());
		proxy.request();
	}
}
