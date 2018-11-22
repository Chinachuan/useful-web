package com.useful.web.config;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Aspect
@Configuration
public class LogAopConfig {
	
	private final Logger logger = LoggerFactory.getLogger(LogAopConfig.class);
	@Autowired
	private Environment env;

	/**
	 * 可以使用逻辑运算符 && ！...
     * execution(com.useful.web.controller..*) &&  within(com.useful.web.*)
	 */
	@Pointcut("within(com.useful.web.controller..*)")
	public void loggerPointcut() {
	}
	
	/**
	 * 配置方式一
	 * @AfterThrowing(pointcut = "loggerPointcut()",throwing = "e")
	 * 配置方式二
	 * @AfterThrowing(pointcut = "execution(* com.useful.web.controller..*(..))",throwing = "e")
	 */
	@AfterThrowing(pointcut = "loggerPointcut()",throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint,Throwable e) {
		if (this.env.acceptsProfiles(new String[]{"dev"})) {
			this.logger.error("异常信息 in {}.{}() with cause = '{}' and exception = '{}'", new Object[]{joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e.getMessage(), e});
		} else {
			this.logger.error("异常信息 in {}.{}() with cause = {}", new Object[]{joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL"});
		}
	}

	
	@Around("loggerPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		/**
		 * joinPoint.getSignature().getDeclaringTypeName()  //类名
		 * joinPoint.getSignature().getName()  // 方法名
         * Arrays.toString(joinPoint.getArgs()); // 参数
		 */
		this.logger.info("传入参数: {}->{}() ==>参数[s] = {}", new Object[]{joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs())});
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Enter: {}.{}() with argument[s] = {}", new Object[]{joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs())});
		}
		try {
			Object result = joinPoint.proceed();
			this.logger.info("返回结果: {}->{}() ==> 返回值 = {}", new Object[]{joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result});
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Exit: {}.{}() with result = {}", new Object[]{joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result});
			}
			return result;
		} catch (IllegalArgumentException ae) {
			this.logger.error("非法参数 argument: {} in {}.{}()", new Object[]{Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()});
			throw ae;
		}
	}
	
	

}
