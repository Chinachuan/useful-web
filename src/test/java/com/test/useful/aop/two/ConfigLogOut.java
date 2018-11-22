package com.test.useful.aop.two;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过配置xml文件通知日志切面
 * @author Admin
 *
 */
public class ConfigLogOut {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(ConfigLogOut.class);
	
	/**
	 * 日志输出
	 * @param pjt
	 * @throws Throwable 
	 */
	public Object logFile(ProceedingJoinPoint pjt) throws Throwable {
		Object proceed = pjt.proceed();
		Object[] args = pjt.getArgs();
		logger.info("==>" + pjt.getSignature().getName() + ",参数为：" + args);
		return proceed;
	}

}
