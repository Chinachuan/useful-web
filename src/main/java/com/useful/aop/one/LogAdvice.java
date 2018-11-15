package com.useful.aop.one;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 方法拦截器
 * @author Admin
 *
 */
public class LogAdvice implements MethodInterceptor{
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(LogAdvice.class);
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		logger.info("==>开始处理{}方法",invocation.getMethod().getName());
		Object proceed = invocation.proceed();
		logger.info("【END】{}方法,处理完成!",invocation.getMethod().getName());
		return proceed;
	}

	

}
