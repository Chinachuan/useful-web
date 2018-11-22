package com.test.useful.context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTest {
	
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//	
//	private MockMvc mockMvc;
//	
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
//	
//	/**
//	 * @Title: whenFindSucc 
//	 * @Date: 2018年11月21日 下午6:25:06
//	 * @author: Jiuchuan.Shi
//	 * @Description: 测试请求/user路径，param传参，设置内容的格式为Json,编码集为utf-8
//	 * 				  判断返回的状态码是否正确，如果返回的是个集合，则断言集合的长度为3
//	 */
//	@Test
//	public void whenFindSucc() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/user")
//				.param("username", "李四")
//				.contentType(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.jsonPath("$ length()").value(3));
//	}
//	
//	@Test
//	public void whenFindSucc01() throws Exception {
//		String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
//				.contentType(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.jsonPath("$ length()").value(3))
//		.andReturn().getResponse().getContentAsString();
//		
//		System.out.println("字符串==>"+contentAsString);
//	}
//	
//	// post 请求
//	@Test
//	public void whenFindSucc02() throws Exception {
//		String parm = ""; // 传的参数,一般为Json字符串
//		mockMvc.perform(MockMvcRequestBuilders.post("/user")
//				.contentType(MediaType.APPLICATION_JSON_UTF8)
//				.content(parm))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
//
//	}
	
	
}
