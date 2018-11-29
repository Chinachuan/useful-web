package com.test.useful.thread;

/**
 * @ClassName: BugSmokeThreadTest
 * @Description: TODO(join测试) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年11月28日 下午7:38:09
 */
public class BugSmokeJoinTest {
	public static void main(String[] args) {
		Father ft = new Father();
		Thread t1 = new Thread(ft);
		t1.start();
	}
}
class Father implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("我让孩子去买烟...");
		try {
			Son sn = new Son();
			Thread t2 = new Thread(sn);
			t2.start();
			t2.join();
			System.out.println("可以抽烟了...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("买烟程序异常...");
		}
	}
}
class Son implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("孩子买来了烟...");
	}
	
}