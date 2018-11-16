package com.useful.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan(basePackages = "com.useful.web")
@EnableAutoConfiguration
@EnableFeignClients("com.useful.api")
public class UsefulWebStart {
	
	private static Logger logger = LoggerFactory.getLogger(UsefulWebStart.class);
	
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

}
