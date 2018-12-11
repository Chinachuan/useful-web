package com.useful.web.controller.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName: TextWebSocketFrameHandler
 * @Description: TODO(
 *  WebSocketFrame 中定义的对应6种帧的类型:
 *		BinaryWebSocketFrame	包含了二进制数据
 *		TextWebSocketFrame 	   包含了文本数据
 *		ContinuationWebSocketFrame 	包含属于上一个BinaryWebSocketFrame或TextWebSocketFrame 的文本数据或者二进制数据
 *		CloseWebSocketFrame 	表示一个CLOSE 请求，包含一个关闭的状态码和关闭的原因
 *		PingWebSocketFrame 	 请求传输一个PongWebSocketFrame
 *		PongWebSocketFrame 	作为一个对于PingWebSocketFrame 的响应被发送
 * ) 
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	/**
	       存
		private static final AttributeKey<String> HOSTADDRESS = AttributeKey.valueOf("key");
		socketChannel.attr(HOSTADDRESS).setIfAbsent(value);
		取
		String value = ctx.channel().attr(HOSTADDRESS).get();
	 */
	// 绑定在Channel上 Channel上的AttributeMap就是大家共享的，每一个ChannelHandler都能获取到。
	private static final AttributeKey<String> HOSTADDRESS = AttributeKey.valueOf("key");
	
	/**
	       存
		public static final AttributeKey<NettyChannel> NETTY_CHANNEL_KEY = AttributeKey.valueOf("netty.channel");  
		Attribute<NettyChannel> attr = ctx.attr(NETTY_CHANNEL_KEY);  
		attr.setIfAbsent(value); 
		取
		value = attr.get();
	 */
	// 绑定在ChannelHandlerContext上  ChannelHandlerContext是唯一的
	// public static final AttributeKey<NettyChannel> NETTY_CHANNEL_KEY = AttributeKey.valueOf("netty.channel");  
	
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception { // (1)
		Channel incoming = ctx.channel();
		for (Channel channel : channels) {
			if (channel != incoming) {
				channel.writeAndFlush(new TextWebSocketFrame("[" + incoming.remoteAddress() + "]" + msg.text()));
			} else {
				channel.writeAndFlush(new TextWebSocketFrame("[you]" + msg.text()));
			}
		}
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
		Channel incoming = ctx.channel();

		// Broadcast a message to multiple Channels
		channels.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 加入"));

		channels.add(incoming);
		System.out.println("Client:" + incoming.remoteAddress() + "加入");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
		Channel incoming = ctx.channel();

		// Broadcast a message to multiple Channels
		channels.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 离开"));

		System.out.println("Client:" + incoming.remoteAddress() + "离开");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
		Channel incoming = ctx.channel();
		System.out.println("Client:" + incoming.remoteAddress() + "在线");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
		Channel incoming = ctx.channel();
		System.out.println("Client:" + incoming.remoteAddress() + "掉线");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) // (7)
			throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("Client:" + incoming.remoteAddress() + "异常");
		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}

}
