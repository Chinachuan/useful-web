package com.useful.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.useful.api.BaseApiResult;
import com.useful.api.user.UserHander;
import com.useful.web.util.ViewUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserHander userHander;

	
	@GetMapping("/login")
	public ModelAndView login(){
		ModelAndView mv = ViewUtil.CreateView("login");
		return mv;
	}
	
	@RequestMapping("/index")
	public String index(Model model,HttpServletRequest req,HttpServletResponse resp) throws Exception{
		BaseApiResult result = userHander.adduser("");
		System.out.println(result.getMesg());
		String userName = req.getParameter("userName");
		String pwd = req.getParameter("pwd");
		model.addAttribute("userName", userName);
		model.addAttribute("pwd", pwd);
		return "index";
	}

}
