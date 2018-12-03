package com.test.useful.socket;

import java.net.MalformedURLException;
import java.net.URL;

public class URLStudy {
	public static void main(String[] args) throws MalformedURLException {
		
		URL url = new URL("https://www.bilibili.com/video/av31450228/?p=5");
		System.out.println("协议"+url.getProtocol());
		System.out.println("域名"+url.getHost());
		System.out.println("端口"+url.getPort());
		System.out.println("请求资源1："+url.getFile());
		System.out.println("请求资源2："+url.getPath());
		
		System.out.println("参数：" + url.getQuery());
		System.out.println("锚点：" + url.getRef());
		
		
		
	}

}
