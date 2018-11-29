package com.test.useful.thread;

/**
 * @ClassName: StaticProxy
 * @Description: TODO(静态代理) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年11月28日 下午5:34:47
 */
public class StaticProxy {
	public static void main(String[] args) {
		new HappyCompany(new you()).happyMarry();
	}
}

interface Marry{
	public void happyMarry();
}

class you implements Marry{
	@Override
	public void happyMarry() {
		// TODO Auto-generated method stub
		System.out.println("洞房花烛夜...");
	}
}

class HappyCompany implements Marry{
	
	private Marry targe; 
	
	public HappyCompany(Marry targe) {
		// TODO Auto-generated constructor stub
		this.targe = targe;
	}

	@Override
	public void happyMarry() {
		// TODO Auto-generated method stub
		targe.happyMarry();
		
	}
	
}
