package com.useful.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginFilter extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle ==> 在控制器方法调用前执行,如果为false则会中断后续的所有操作...");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("postHandle ==> 在控制器方法调用后，解析视图前调用，我们可以对视图和模型做进一步渲染或修改");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("afterCompletion ==> 整个请求完成，即视图渲染结束后调用，这个时候可以做些资源清理工作或日志记录");
		super.afterCompletion(request, response, handler, ex);
	}
	
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("afterConcurrentHandlingStarted ==> 方法用于处理异步请求，当Controller中有异步请求方法的时候会触发该方法时");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
	

}
