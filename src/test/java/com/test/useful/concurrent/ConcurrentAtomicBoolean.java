package com.test.useful.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.util.LongAdder;


/**
 * @ClassName: ConcurrentTest
 * @Description: TODO(只执行一次) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年12月4日 下午2:32:22
 */
public class ConcurrentAtomicBoolean {
	private static Logger logger = LoggerFactory.getLogger(ConcurrentAtomicBoolean.class);
	public static int threadTotal = 200;
	public static int clientTotal = 5000;
	
	/**
	 *  LongAdder在AtomicLong的基础上将单点的更新压力分散到各个节点，
	 *  在低并发的时候通过对base的直接更新可以很好的保障和AtomicLong的性能基本保持一致，
	 *  而在高并发的时候通过分散提高了性能。 
     *  缺点是LongAdder在统计的时候如果有并发更新，可能导致统计的数据有误差。
	 */
	public static LongAdder count = new LongAdder();
	public static AtomicBoolean boolean1 = new AtomicBoolean(false);
	
	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						// 控制信号灯
						semaphore.acquire();
						add();
						semaphore.release();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 每次执行完，模拟的客户端总量减1
					countDownLatch.countDown();
				}
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		logger.info("count:{}",count);
	}
	private static void add() {
		// 如果等于false,则把值修改为true
		if (boolean1.compareAndSet(false, true)) {
			count.increment();
		}
		
	}

}
