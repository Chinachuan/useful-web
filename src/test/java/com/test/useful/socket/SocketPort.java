package com.test.useful.socket;

import java.net.InetSocketAddress;

/**
 * @ClassName: SocketPort
 * @Description: TODO(学习端口) 
 * @author: Jiuchuan.Shi
 * @Date: 2018年12月1日 下午10:10:56
 */
public class SocketPort {
	
	/**
	 * 端口
	 * 1、用于区别软件
	 * 2、2个字节 0-65535 TCP UDP
	 * 3、同一个协议下，端口不能冲突
	 * 4、端口越大越好
	 * 
	 */
	public static void main(String[] args) {
		InetSocketAddress isa01 = new InetSocketAddress("127.0.0.1",8080);
		InetSocketAddress isa02 = new InetSocketAddress("localhost",9000);
		System.out.println(isa01.getHostName());
		System.out.println(isa02.getAddress());
		System.out.println(isa02.getPort());
	}

}
