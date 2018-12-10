package com.useful.web.controller.netty;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

/**
 * @ClassName: NettyService
 * @Description: TODO(创建Netty服务器) 
 */
public class NettyService{
	
	private static Logger logger = LoggerFactory.getLogger(NettyService.class);
	
	private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup bossGroup = new NioEventLoopGroup();
	private final EventLoopGroup workerGroup = new NioEventLoopGroup();
	private Channel channel;
	
	public ChannelFuture nettyRun(int port) throws Exception {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new WebsocketChatServerInitializer())
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
		ChannelFuture future = bootstrap.bind(port).sync();
		channel = future.channel();
		logger.info("NettyService 已经启动...");
		return future;
	}
	
	public void destroy() throws Exception {
		if(channel != null) {
			channel.closeFuture().sync();
		}
		channelGroup.close();
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
		logger.info("NettyService 已停止运行【END】");
	}
}
	
