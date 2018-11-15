package com.useful.aop.one;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class TestMain {
	
	public static void main(String[] args) {
		
		TestMain tm = new TestMain();
		ProxyFactory proxy = tm.getProxy();
		UserSevice use = (UserSevice) proxy.getProxy();
		use.addUser();
		use.delUser();
		use.updateUser();
		use.qryUser();
	}
	
	
	public ProxyFactory getProxy() {
		/**
		 *  声明【切点】，配置日志打印规则
		 */
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.addMethodName("del*");
		pointcut.addMethodName("update*");
		pointcut.addMethodName("add*");
		
		/**
		 *  创建【通知】
		 */
		LogAdvice advice = new LogAdvice();
		
		/**
		 *  创建【切面】，切面=通知+切点
		 */
	    Advisor advisor = new DefaultPointcutAdvisor(pointcut,advice);
	    
	    /**
	     *  利用代理机制，把切面【织入】到业务中
	     */
	    // 创建代理工厂
	    ProxyFactory pf = new ProxyFactory();
	    // 创建要代理的业务
	    UserSevice us = new UserSeviceImpl();
	    // 把业务交给工厂,【指定目标对象】
	    pf.setTarget(us);
	    // 增加代理的日志切面
	    pf.addAdvisor(advisor);
		return pf;
	}

}
