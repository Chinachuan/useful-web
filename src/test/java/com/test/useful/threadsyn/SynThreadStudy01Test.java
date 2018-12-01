package com.test.useful.threadsyn;

/**
 * @ClassName: SynThreadStudyTest
 * @Description: TODO(买电影票) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年11月29日 下午3:48:41
 */
public class SynThreadStudy01Test{
	
	/**
	      使用synchronized 的不足之处：
	 
	       第一大不足：由于我们没办法设置synchronized关键字在获取锁的时候等待时间，
	       所以synchronized可能会导致线程为了加锁而无限期地处于阻塞状态。
		
		第二大不足：使用synchronized关键字等同于使用了互斥锁，即其他线程都无法获得锁对象的访问权。
		这种策略对于读多写少的应用而言是很不利的，
		因为即使多个读者看似可以并发运行，但他们实际上还是串行的，并将最终导致并发性能的下降。
		*/
	
	public static void main(String[] args) {
		Yingyuan c = new Yingyuan(30,"京都影院");
		new Thread(new Cousumer(c, 3)).start();
		new Thread(new Cousumer(c, 6)).start();
	}

}

class Cousumer implements Runnable{
	Yingyuan yingyuan;
	int seats;
	
	public Cousumer(Yingyuan yingyuan,int seats) {
		this.yingyuan = yingyuan;
		this.seats = seats;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (yingyuan) {
			boolean bugTicket = yingyuan.bugTicket(seats);
			if(bugTicket) {
				System.out.println(Thread.currentThread().getName() + " 购票成功,购票数量" + seats);
			}else {
				System.out.println(Thread.currentThread().getName() + " 购票失败,票数不足");
			}
		}
	}
	
}

class Yingyuan{
	private int ticket;
	private String yName;
	
	public Yingyuan(int ticket,String yName) {
		this.ticket = ticket;
		this.yName = yName;
	}
	
	public boolean bugTicket(int numbers) {
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