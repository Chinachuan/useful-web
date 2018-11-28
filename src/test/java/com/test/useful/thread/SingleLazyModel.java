package com.test.useful.thread;


/**
 * @ClassName: SingleHungryModel
 * @Description: TODO(单例懒汉模式) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年11月27日 下午6:11:13
 */
public class SingleLazyModel {
	
	private static SingleLazyModel SHM = null;
	
	private SingleLazyModel() {
		
	}
	
    public static SingleLazyModel getInStanceBlock(){
    	
        if(SHM==null) {
        	synchronized (SingleLazyModel.class) {
                if(SHM==null) {
                	SHM = new SingleLazyModel();
                }
            }
        }
        return SHM;   
    }
}
