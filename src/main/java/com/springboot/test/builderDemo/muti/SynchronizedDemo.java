package com.springboot.test.builderDemo.muti;

public class SynchronizedDemo {

	public static void main(String[] args) {
		SynchronizedDemo demo1 = new SynchronizedDemo();
		// 修饰对象实列方法 锁定的当前实列对象 必须是公用同一个对象才行
		/*new Thread("thread1-1"){
			
			@Override
			public void run(){
				try {
					//
					demo1.demo1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread("thread1-2"){
			
			@Override
			public void run(){
				try {
					demo1.demo1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();*/
		/**
		 * 修饰对象静态方法
		 * 静态方法是类方法 静态方法即类方法，它属于一个类而不是某个对象
		 * 在其上面加锁 锁的对象为类对象
		 */
		/*new Thread("thread2-1"){
			
			@Override
			public void run(){
				try {
					SynchronizedDemo.demo2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread("thread2-2"){
			
			@Override
			public void run(){
				try {
					SynchronizedDemo.demo2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();*/
		/**
		 * 修饰对象实列方法-代码块 this代表当前对象 
		 * 3与1 2 不是同一个对象 13同时执行 12是同步执行
		 */
		/*SynchronizedDemo demo3 = new SynchronizedDemo();
		new Thread("thread3-1"){
			
			@Override
			public void run(){
				try {
					demo3.demo3();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread("thread3-2"){
			
			@Override
			public void run(){
				try {
					demo3.demo3();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread("thread3-3"){
			
			@Override
			public void run(){
				try {
					new SynchronizedDemo().demo3();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();*/
		// 修饰对象静态方法-代码块
		// 修饰对象实列方法-代码块-当前对象
		// 修饰对象实列方法-代码块-指定对象
		/**
		 * 修饰对象实列方法-代码块-指定对象
		 * 这里new了两个SynchronizedBlock对象 但由于指定同步对象是SynchronizedDemo
		 * 所以方法依然会同步执行
		 */
		SynchronizedDemo demo4 = new SynchronizedDemo();
		new Thread("thread4-1"){
			
			@Override
			public void run(){
				try {
					new SynchronizedBlock(demo4).demo3();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread("thread4-2"){
			
			@Override
			public void run(){
				try {
					new SynchronizedBlock(demo4).demo3();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		// 修饰对象实列方法-代码块-类对象
		// 修饰对象静态方法-代码块-当前对象
		// 修饰对象静态方法-代码块-指定对象
		// 修饰对象静态方法-代码块-类对象
		
	}
	
	/**
	 * 修饰实列方法
	 * @throws InterruptedException 
	 */
	public synchronized void demo1() throws InterruptedException{
		System.out.println("当前访问的线程名称是===="+Thread.currentThread().getName());
		//休眠2s 造成两个线程同时访问本实列方法
		Thread.sleep(2000);
	}
	
	/**
	 *修饰静态方法 
	 * @throws InterruptedException 
	 */
	public synchronized static void demo2() throws InterruptedException{
		System.out.println("当前访问的线程名称是===="+Thread.currentThread().getName());
		Thread.sleep(2000);
	}
	/**
	 * 修饰实列方法代码块
	 * @throws InterruptedException 
	 */
	public void demo3() throws InterruptedException{
		synchronized(this){
			System.out.println("当前访问的线程名称是===="+Thread.currentThread().getName());
			Thread.sleep(2000);
		}
	}
	/**
	 * 修饰静态方法代码块
	 * @throws InterruptedException 
	 */
	public static void demo4() throws InterruptedException{
		synchronized(SynchronizedBlock.class){
			System.out.println("当前访问的线程名称是===="+Thread.currentThread().getName());
			Thread.sleep(2000);
		}
	}
}

class SynchronizedBlock {
	//声明一个实例变量
    private SynchronizedDemo demo;
    SynchronizedBlock(SynchronizedDemo demo){
         this. demo= demo;
    }
    /**
     * 同步当前对象
     * @throws InterruptedException
     */
	public void demo3() throws InterruptedException{
		synchronized(this){
			System.out.println("当前访问的线程名称是===="+Thread.currentThread().getName());
			Thread.sleep(2000);
		}
	}
    /**
     * 同步指定对象
     * @throws InterruptedException
     */
	public void demo3_1() throws InterruptedException{
		synchronized(demo){
			System.out.println("当前访问的线程名称是===="+Thread.currentThread().getName());
			Thread.sleep(2000);
		}
	}
	/**
	 * 同步class类对象
	 * @throws InterruptedException
	 */
	public void demo3_2() throws InterruptedException{
		synchronized(SynchronizedBlock.class){
			System.out.println("当前访问的线程名称是===="+Thread.currentThread().getName());
			Thread.sleep(2000);
		}
	}
}