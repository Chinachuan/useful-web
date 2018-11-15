package com.useful.web.util;

import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: ViewUtil
 * @Description: TODO(创建一个视图) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年3月30日 上午10:55:50
 */
public class ViewUtil {
	
	// 创建一个视图
	public static ModelAndView CreateView(String pages){
		ModelAndView mv = new ModelAndView(pages);
		return mv;
	}

}
