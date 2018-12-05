package com.test.useful.concurrent;

import java.util.Arrays;

/**
 * @ClassName: ConcurrentPublish
 * @Description: TODO(发布与逸出) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年12月5日 上午10:29:31
 */
public class ConcurrentPublish {
	
	private String[] state = {"a","b","c"};
	
	public String[] getState() {
		return state;
	}
	
	public static void main(String[] args) {
		ConcurrentPublish cp = new ConcurrentPublish();
		System.out.println(Arrays.toString(cp.getState()));
		
		cp.getState()[0] = "e";
		System.out.println(Arrays.toString(cp.getState()));
	
	}

}
