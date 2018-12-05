package com.test.useful.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @ClassName: ReentrantLockCondition
 * @Description: TODO(ReentrantLock 学习) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年12月5日 下午5:13:13
 */
public class ReentrantLockCondition {

	public static void main(String[] args) {

		final ReentrantLock reentrantLock = new ReentrantLock();
		final Condition newCondition = reentrantLock.newCondition();
		
		ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		ReadLock readLock = readWriteLock.readLock();
		WriteLock writeLock = readWriteLock.writeLock();
		System.out.println("这是读写锁==>" + readLock + writeLock);

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				reentrantLock.lock();
				try {
					System.out.println("输出==> 1 ");
					newCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					reentrantLock.unlock();
				}
				System.out.println("输出==> 2 ");

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				reentrantLock.lock();
				try {
					System.out.println("输出==> 3 ");
					newCondition.await();
//					newCondition.signalAll();
//					newCondition.signal();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					reentrantLock.unlock();
				}
				System.out.println("输出==> 4 ");
			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				reentrantLock.lock();
				try {
					System.out.println("输出==> 5 ");
					newCondition.signalAll();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					reentrantLock.unlock();
				}
				System.out.println("输出==> 6 ");
			}
		}).start();

	}

}
