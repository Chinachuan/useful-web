package com.test.useful.thread;

public class ThreadClassStudyTest extends Thread{
	
	/**
	 * 默认： JVM等待用户线程执行完毕才会停止
	 *
	 */
	
	public static void main(String[] args) {
		
		ThreadClass01Study t1 = new ThreadClass01Study(1);
		ThreadClass01Study t2 = new ThreadClass01Study(2);
		ThreadClass01Study t3 = new ThreadClass01Study(3);
		ThreadClass01Study t4 = new ThreadClass01Study(4);
		ThreadClass01Study t5 = new ThreadClass01Study(5);
		ThreadClass01Study t6 = new ThreadClass01Study(6);
		ThreadClass01Study t7 = new ThreadClass01Study(7);
		/**
		 * 设置线程的优先级 1-10
		 * MAX_PRIORITY 最大为 10
		 * MIN_PRIORITY 最小为 1
		 * NORM_PRIORITY 默认为5
		 * 不代表绝对优先，只是优先的概率更大了
		 */
//		t1.setDaemon(true); // 将用户线程改变成守护线程，JVM不用等待线程执行完毕
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		t3.setPriority(Thread.MIN_PRIORITY);
		t4.setPriority(Thread.MIN_PRIORITY);
		t5.setPriority(Thread.NORM_PRIORITY);
		t6.setPriority(Thread.NORM_PRIORITY);
		t7.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		
		// 接口的方式启动线程
		ThreadClass02Study t01 = new ThreadClass02Study("C:/test01.txt","test01.txt");
		ThreadClass02Study t02 = new ThreadClass02Study("C:/test02.txt","test02.txt");
		ThreadClass02Study t03 = new ThreadClass02Study("C:/test03.txt","test03.txt");
		
//		Thread thread1 = new Thread(t01);
//		Thread thread2 = new Thread(t02);
//		Thread thread3 = new Thread(t03);
//		thread1.start();
//		thread2.start();
//		thread3.start();
		new Thread(t01).start();
		new Thread(t02).start();
		new Thread(t03).start();
		
		// 简化线程开发
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("线程简化开发模式...");
//			}
//		}).start();
		
		// java8 模式 lambda模式
//		new Thread(()->{
//			System.out.println("Java 8 创建线程模式...");
//		}).start();
		
		
		
		
	}

}
