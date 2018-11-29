package com.test.useful.thread;

public class YieldThreadTest implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("线程 " + Thread.currentThread().getName() + " start...");
		Thread.yield();
		System.out.println("线程 " + Thread.currentThread().getName() + " end...");
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			try {
//				Thread.sleep(300);
				YieldThreadTest yt = new YieldThreadTest();
				new Thread(yt,"杨========>"+i).start();
				new Thread(yt,"杨**湿====>"+i).start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


}
