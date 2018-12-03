package com.test.useful.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SocketAddress {
	
	public static void main(String[] args) throws UnknownHostException {
		// 47.98.146.48
		// 获取本机的连接信息
		InetAddress byName = InetAddress.getLocalHost();
		System.out.println(byName.getHostName());
		System.out.println(byName.getHostAddress());
		
		InetAddress byName01 = InetAddress.getByName("www.chatweb.vip");
		System.out.println(byName01.getHostAddress());
		
		InetAddress byName02 = InetAddress.getByName("47.98.146.48");
		System.out.println(byName02.getHostName()); // 输出的依然是ip地址，或者可能是DNS不允许
		
		System.out.println("cmd 查看所有端口 netstat -ano");
		System.out.println("cmd 查看指定端口 netstat -ano '8080' ");
		System.out.println("cmd 查看指定进程 tasklist '8080'");

		
	}
}
