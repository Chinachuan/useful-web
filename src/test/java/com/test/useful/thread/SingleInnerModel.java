package com.test.useful.thread;


/**
 * @ClassName: SingleHungryModel
 * @Description: TODO(单例内部类模式) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年11月27日 下午6:11:13
 */
public class SingleInnerModel {
	
	/**
     * jvm实例化内部类线程是安全的
	 */
	private static class InnerModel{
		static SingleInnerModel SHM = new SingleInnerModel();
	}
	
	private SingleInnerModel() {
		
	}
	
	public static SingleInnerModel getShm() {
		 return InnerModel.SHM;
		 
	} 
	

}
