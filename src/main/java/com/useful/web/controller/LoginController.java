package com.useful.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.useful.api.utils.VerificationCodeUtils;

@Controller
public class LoginController extends BaseController{
	
	private static final String V_CODE = "randomCode";
	public static final String LOGIN_INFO = "loginInfo";
	private static final String IP_ERROR_MSG = "IP_ERROR_MSG";
	
	@GetMapping(value = "randomCode")
	public void randomCode(HttpServletResponse response, HttpSession session) {
		String code = VerificationCodeUtils.generateCode(4);
		BufferedImage image = VerificationCodeUtils.generateImage(code);
		session.setAttribute(V_CODE, code);
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(image, "JPEG", sos);
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping(value = "login")
	public String login(HttpServletRequest req, HttpServletResponse response) throws NoSuchAlgorithmException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute(LOGIN_INFO) != null) {
			JSONObject loginInfo = (JSONObject)session.getAttribute(LOGIN_INFO);
			JSONArray menus = (JSONArray)loginInfo.get("menu");
			LOGGER.info("menus_info-----"+ menus); 
			if(menus!=null && menus.size()>0){
				JSONObject menu = (JSONObject)menus.get(0);
				JSONArray subMenus  =(JSONArray) menu.get("menus");
				if(subMenus!=null&&subMenus.size()>0){
					menu = (JSONObject)subMenus.get(0);
				}
				if(menu!=null){
					String url = menu.getString("path");
					return "redirect:"+url;
				}
			}
		}
		String ipErrorMsg = (String) req.getAttribute(IP_ERROR_MSG);
		req.setAttribute("msg", ipErrorMsg);
		VerificationCodeUtils.initRSA(req);
		return "login";
	}
	
	@PostMapping(value = "login")
	public String loginPost(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
							String loginName, String password, String vcode) throws NoSuchAlgorithmException {
		String code2 = (String) session.getAttribute(V_CODE);
		//判断验证码
		if (StringUtils.isBlank(vcode)) {
			req.setAttribute(IP_ERROR_MSG, "验证码不能为空");
			return login(req, resp);
		}
		if (!vcode.equalsIgnoreCase(code2)) {
			req.setAttribute(IP_ERROR_MSG, "验证码不正确");
			return login(req, resp);
		}
		
		
		
		return "redirect:/user/index";
	}

}
