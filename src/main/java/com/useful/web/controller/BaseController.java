package com.useful.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;

public class BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	public static final String PUB_KEY_PATH = "pubKeyPath";

	public static final String NET_TYPE = "net";

	/**
	 * 拦截controller 未知异常.
	 */
	@ExceptionHandler({Exception.class})
	public String processRuntimeException(HttpServletRequest request, Exception ex) {
		if (ex instanceof FeignException) {
			if (((FeignException) ex).status() == HttpStatus.UNAUTHORIZED.value()) {
				request.getSession().invalidate();
				LOGGER.error("远程用户session过期,重新登录", ex);
				return "redirect:/login";
			}
			LOGGER.error("远程调用异常", ex);
		} else {
			LOGGER.error("程序运行时异常", ex);
		}
		request.setAttribute("message", ex.getMessage());
		return "error/404";
	}


}
