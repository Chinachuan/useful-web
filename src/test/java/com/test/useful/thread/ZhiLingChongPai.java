package com.test.useful.thread;


public class ZhiLingChongPai {
	private static int mk = 0;
	static boolean flag = false;
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mk = 1;
				flag = true;
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (flag) {
					mk = 0;
					System.out.println("状态修改成功...");
				}
				if(mk == 0) {
					System.out.println("指令重排了...");
				}
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

}
