package com.test.useful.thread;

import java.util.ArrayList;
import java.util.List;

public class SingThreadTest {
	
	
	public static void singThread() {
//		同步并发容器
//		final CopyOnWriteArrayList<Integer> sscc = new CopyOnWriteArrayList<>();
//		sscc.add(1);
		
		final List<Integer> succ = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			Thread td = new Thread(new Runnable() {
				@Override
				public void run() {
					SingleLazyModel shm = SingleLazyModel.getInStanceBlock();
					// 保证线程是同步的
					synchronized (succ) {
						if(succ.contains(shm.hashCode())) {
							System.out.println("=================已包含================");
						}else {
							System.out.println("=======  ###########################  =========");
							succ.add(shm.hashCode());
						}
					}
					System.out.println("=========================" + succ.size());
				}
			});
			td.start();
		}
	}
	public static void main(String[] args) {
		SingThreadTest.singThread();
	}

}
