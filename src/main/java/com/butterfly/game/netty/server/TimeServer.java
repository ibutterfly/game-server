package com.butterfly.game.netty.server;

import com.butterfly.game.netty.decoder.MessageDecoder;
import com.butterfly.game.netty.encoder.MessageEncoder;
import com.butterfly.game.netty.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import org.springframework.stereotype.Component;

/**
 * Created by 杨国 on 2019/10/14.
 *
 * @author yangguo
 */
@Component
public class TimeServer {
    private int port = 88888;

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGrgoup = new NioEventLoopGroup();
        ByteBuf heapBuffer = Unpooled.buffer(8);
        heapBuffer.writeBytes("\r".getBytes());
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGrgoup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("encoder", new MessageEncoder())
                                    .addLast("decoder", new MessageDecoder())
                                    //防止TCP粘包/拆包
                                    .addFirst(new LineBasedFrameDecoder(65535))
                                    //ServerHandler实现了业务逻辑
                                    .addLast(new ServerHandler());
                        }
                    })
                    //服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。
                    // 可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能。
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //绑定服务器，等待绑定完成，调用sync()的原因是当前线程阻塞
            ChannelFuture f = b.bind(port).sync();
            //关闭channel和块，直到它被关闭
            f.channel().closeFuture().sync();
        } finally {
            //关闭EventLoopGroup，释放所有资源（包括所有创建的线程）
            workerGrgoup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public void start(int port) throws InterruptedException {
        this.port = port;
        this.run();
    }
}
