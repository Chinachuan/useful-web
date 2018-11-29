package com.test.useful.thread;

/**
 * @ClassName: ThreadRandomTest
 * @Description: TODO(线程随机性测试) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年11月28日 上午11:16:21
 */
public class ThreadRandomTest extends Thread{
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				int stime  = (int) (Math.random() * 1000);
				Thread.sleep(stime);
				System.out.println("name for the current thread is : " + Thread.currentThread());
				System.out.println("name for the current thread is : " + Thread.currentThread().getName());
				System.out.println("name for the current thread is : " + Thread.currentThread().getId());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		ThreadRandomTest trt = new ThreadRandomTest();
		trt.setName("class thread");
		trt.start();
		/**
		 * 如果使用run()方法启动线程，那当前main方法的执行就会变成同步的，
		 * 即执行完线程之后再继续往下执行
		 * 而使用start()启动线程，则是另外开启了一个新的线程
		 */
//		trt.run(); 
		for (int i = 0; i < 10; i++) {
			try {
				int stime  = (int) (Math.random() * 1000);
				Thread.sleep(stime);
				System.out.println("name for the current main thread is : " + Thread.currentThread().getId());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
