package com.test.useful.thread;


/**
 * @ClassName: SingleHungryModel
 * @Description: TODO(单例饿汉模式) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年11月27日 下午6:11:13
 */
public class SingleHungryModel {
	
	private static SingleHungryModel SHM = new SingleHungryModel();
	
	private SingleHungryModel() {
		
	}
	
	public static SingleHungryModel getShm() {
		  return SHM;
	} 
	

}
