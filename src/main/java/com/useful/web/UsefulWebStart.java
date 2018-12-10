package com.useful.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import com.useful.web.controller.netty.NettyService;

import io.netty.channel.ChannelFuture;

@ComponentScan(basePackages = "com.useful.web")
@EnableAutoConfiguration
@EnableFeignClients("com.useful.api")
public class UsefulWebStart implements CommandLineRunner {
	
	private static Logger logger = LoggerFactory.getLogger(UsefulWebStart.class);
	
	@Autowired
	private NettyService nettyService;
	
	@Order(value=1)
	public static void main(String[] args) throws UnknownHostException {
		Environment env = SpringApplication.run(UsefulWebStart.class, args).getEnvironment();
		logger.info("\n----------------------------------------------------------\n\t" +
				"Application '{}' is running! Access URLs:\n\t" +
				"Local: \t\thttp://localhost:{}\n\t" +
				"External: \t\thttp://{}:{}\n\t----------------------------------------------------------",
		env.getProperty("spring.application.name"),
		env.getProperty("server.port"),
		InetAddress.getLocalHost().getHostAddress(),
		env.getProperty("server.port"),
		env.getProperty("server.port")); 
		logger.info("\n----------------------------------------------------------\n\t");
	}

	@Bean
	public NettyService nettyService() {
		return new NettyService();
	}
	
	@Order(value=2)
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		ChannelFuture future = new NettyService().nettyRun(9996);
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				logger.info("[系统消息]:chat-web 开始注销 !");
				try {
					nettyService.destroy();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		future.channel().closeFuture().syncUninterruptibly();
	}

}
