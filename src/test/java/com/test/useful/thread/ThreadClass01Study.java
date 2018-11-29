package com.test.useful.thread;

public class ThreadClass01Study extends Thread{
	
	private int i;
	
	public ThreadClass01Study(int i) {
		this.i = i;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("current thread name in class " + i);
	}

}
