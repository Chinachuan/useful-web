package com.test.useful.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ConcurrentFutureTask {
	
	public static void main(String[] args) throws Exception {
		
		FutureTask<String> futureTask01 = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName() + "开始写源码...");
				Thread.sleep(3000);
				return Thread.currentThread().getName() + "任务完成";
			}
		});
		
		FutureTask<String> futureTask02 = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName() + "开始写源码...");
				Thread.sleep(3000);
				return Thread.currentThread().getName() + "任务完成";
			}
		});
		
		Thread t1 = new Thread(futureTask01,"喻鹏");	
		Thread t2 = new Thread(futureTask02,"海伟");
		t1.start();
		t2.start();
		
		System.out.println("我是在两个线程开始之后执行的...");
		
		String str01 = futureTask01.get();
		System.out.println("wo shi yu peng:" + str01);
		
		String str02 = futureTask02.get();
		System.out.println("wo shi hai wei: " + str02);
		
	
		
		
	}
	
	

}
