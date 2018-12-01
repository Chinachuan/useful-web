package com.test.useful.threadsyn;

/**
 * @ClassName: SynThreadStudyTest
 * @Description: TODO(买火车票) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年11月29日 下午3:48:41
 */
public class SynThreadStudy02Test{
	public static void main(String[] args) {
		Web12306 c = new Web12306(30,"京都影院");
		new ChengKe(c, "小明", 5).start();
		new ChengKe(c, "小网", 10).start();
	}

}

class ChengKe extends Thread{
	int seats;
	public ChengKe(Runnable targe,String name,int seats) {
		super(targe,name);
		this.seats =seats;
	}
}

class Web12306 implements Runnable{
	private int ticket;
	private String yName;
	
	public Web12306(int ticket,String yName) {
		this.ticket = ticket;
		this.yName = yName;
	}
	
	@Override
	public void run() {
		ChengKe name = (ChengKe)Thread.currentThread();
		boolean bugTicket = this.bugTicket(name.seats);
		if(bugTicket) {
			System.out.println(Thread.currentThread().getName() + " 购票成功,购票数量" + name.seats);
		}else {
			System.out.println(Thread.currentThread().getName() + " 购票失败,票数不足");
		}
	}
	
	public synchronized boolean bugTicket(int numbers) {
		System.out.println(yName + "影院当前剩余票数############:" + ticket);
		if(numbers > ticket) {
			System.out.println("票数不足，购票失败...");
			return false;
		}else {
			System.out.println("当前票数充足，购票成功...");
			ticket -= numbers;
			System.out.println(yName + "影院当前剩余票数=======>" + ticket);
			return true;
		}
	}
	
}