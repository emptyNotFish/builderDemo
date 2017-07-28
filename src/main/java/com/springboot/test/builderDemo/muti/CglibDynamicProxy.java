package com.springboot.test.builderDemo.muti;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibDynamicProxy implements MethodInterceptor {

	/**
	 * 内部维护一个被代理对象
	 */
	private Object targert;
	
	public CglibDynamicProxy(Object targert) {
		super();
		this.targert = targert;
	}
	/**
	 * 创建代理对象并返回
	 * @return
	 */
	public Object getProxyInstance(){
		//1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(targert.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
	}
	@Override
	public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("代理方法前执行");
		//调用被代理对象的方法
		method.invoke(targert, args);
		System.out.println("代理方法后执行");
		return null;
	}

	public static void main(String[] args) {
		User user = new User();
		//生成代理对象
		User proxy = (User) new CglibDynamicProxy(user).getProxyInstance();
		proxy.save();
	}
}
