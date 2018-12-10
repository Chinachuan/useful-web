package com.useful.web.controller.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebsocketChatServerInitializer extends ChannelInitializer<SocketChannel> { // 1

	@Override
	public void initChannel(SocketChannel ch) throws Exception {// 2
		ChannelPipeline pipeline = ch.pipeline();
		
		// 处理日志
		pipeline.addLast(new LoggingHandler(LogLevel.INFO));

        /**
         * 将字节解码为HttpRequest、HttpContent 和LastHttpContent。
		 * 并将HttpRequest、HttpContent 和LastHttpContent 编码为字节
		 */
		pipeline.addLast(new HttpServerCodec());
		
		// 将http消息的多个部分组成一条完整的Http消息
		pipeline.addLast(new HttpObjectAggregator(64 * 1024));
		
		// 写入一个文件的内容
		pipeline.addLast(new ChunkedWriteHandler());
		
		// 处理FullHttpRequest(那些不发送到/websocket URI的请求)
		pipeline.addLast(new HttpRequestHandler("/websocket"));
		
		/**
		 *  按照WebSocket 规范的要求，处理WebSocket 升级握手、PingWebSocketFrame 、PongWebSocketFrame 
		 *  和CloseWebSocketFrame
		 */
		pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));
		
		// 处理TextWebSocketFrame 和握手完成事件
		pipeline.addLast(new TextWebSocketFrameHandler());

	}
}
