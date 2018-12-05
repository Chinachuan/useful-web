package com.test.useful.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName: ConcurrentTest
 * @Description: TODO(高并发学习) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年12月4日 下午2:32:22
 */
public class ConcurrentTest {
	private static Logger logger = LoggerFactory.getLogger(ConcurrentTest.class);
	public static int threadTotal = 200;
	public static int clientTotal = 5000;
	public static AtomicInteger count = new AtomicInteger(0);
	
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
		logger.info("count:{}",count.get());
	}
	private static void add() {
		count.incrementAndGet();
	}

}
