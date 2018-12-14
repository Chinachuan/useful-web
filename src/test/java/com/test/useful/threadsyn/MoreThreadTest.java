package com.test.useful.threadsyn;

public class MoreThreadTest {
	
	/**
	 * 附记：
	 * 		// 线程不安全
	 * 	   	SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	 * 		// 线程安全,java8之后才添加的
	 * 		DataTimeFormat dataTimeFormat = new DataTimeFormat()
	 * 		
	 */
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				TestThread tts = new TestThread();
				
				for (int i = 0; i < 10000; i++) {
					String name = String.valueOf(i);
					Thread td = new Thread(tts,name);
					td.start();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("OK-执行完毕-JVM已经关了。");
			}
		});
		Runtime.getRuntime().addShutdownHook(t2);
		t1.start();
	}
}

class TestThread implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("线程" + Thread.currentThread().getName() + "发起调用...");
	}
}