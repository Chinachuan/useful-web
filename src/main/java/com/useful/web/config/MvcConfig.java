package com.useful.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public LoginFilter LoginFilter() {
		return new LoginFilter();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(LoginFilter()).addPathPatterns("/**").excludePathPatterns("login.html","login","randomCode");
		super.addInterceptors(registry);
	}

}
