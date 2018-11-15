package com.useful.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.useful.web.util.ViewUtil;

@RestController
public class UserController {
	
	@RequestMapping(value="/user/login")
	public ModelAndView UserLogin(){
		ModelAndView mv = ViewUtil.CreateView("login");
		return mv;
	}

}
