package com.useful.web.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.useful.web.util.ViewUtil;

//@RestController 标明Controller是使用Restful Api的
@RestController
@RequestMapping("/test")
public class TestController {
	
	/**
	 * Restful 特点：用url描述资源
	 *       使用http方法描述方法，使用http状态码来表示返回的结果
	 *       使用json 进行数据交互
	 * @GetMapping("/user/{id}")
	 * @PostMapping
	 * @PutMapping
	 * @DeleteMapping
	 * 
	 * @RequestParam String name 使用此注解，如果不传值，就会报错,@RequestParam(name="username") 指定配置的参数名字
	 * 				 required=false,defaultValue = "张三"   如果没有传值，则给一个默认值
	 */
//	@PostMapping
	@RequestMapping(value = "/restful",method = RequestMethod.GET)
	public ModelAndView restful(@RequestParam(name = "username",required=false,defaultValue = "张三") String name){
		ModelAndView mv = ViewUtil.CreateView("restfuOl");
		String randomNumeric = RandomStringUtils.randomNumeric(8);
		System.out.println("随机生成一个8位数的字符串"+randomNumeric);
//		System.out.println(ReflectionToStringBuilder.toString(new Test(),ToStringStyle.MULTI_LINE_STYLE));
		return mv;
	}
	
	/**
	 * @PathVariable 注解，意思是把{id}当做一个片段，它的值，做为参数传入到方法中
	 * (value = "/restful/{id:\\+d}) 只能接受数字
	 */
	@RequestMapping(value = "/restful/{id}",method = RequestMethod.POST)
	public ModelAndView restful01(@PathVariable String id){
		ModelAndView mv = ViewUtil.CreateView("restful");
//		System.out.println(ReflectionToStringBuilder.toString(new Test(),ToStringStyle.MULTI_LINE_STYLE));
		return mv;
	}
	
	
	

}
