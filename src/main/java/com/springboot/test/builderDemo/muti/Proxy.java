package com.springboot.test.builderDemo.muti;

public class Proxy implements Subject {

	private Subject subject;
	
	public Proxy(Subject subject) {
        this.subject = subject;
	}
	
	@Override
	public void request() {
		System.out.println("Proxy before subject start!!!!!!!");
		subject.request();
		System.out.println("Proxy after subject start-------");
	}
	
	public static void main(String[] args) {
		Subject subject = new RealSubject();
		//生成代理类
		Proxy proxy = new Proxy(subject);
		proxy.request();
	}

}
