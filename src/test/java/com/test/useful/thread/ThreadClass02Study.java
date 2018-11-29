package com.test.useful.thread;

/**
 * @ClassName: ThreadClass02Study
 * @Description: TODO(推荐使用接口的方式创建一个线程，这样就可以避免单继承的局限性) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年11月28日 下午4:28:27
 */
public class ThreadClass02Study implements Runnable{
	
	private String fileUrl;
	private String fileName;
	
	public ThreadClass02Study(String fileUrl,String fileName) {
		this.fileUrl = fileUrl;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("传入的文件地址和文件名称：" + fileUrl + "," + fileName);
	}
	
	
}
