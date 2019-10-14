package com.butterfly.game.netty.handler;

import com.butterfly.game.netty.invote.ActionMapUtil;
import com.butterfly.game.netty.message.Header;
import com.butterfly.game.netty.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by 杨国 on 2019/10/14.
 * 处理并分发
 *
 * @author yangguo
 */
public class ServerHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message m = (Message) msg;
        Header header = m.getHeader();
        /* 请求分发*/
        ActionMapUtil.invote(header.getCommand(), ctx, m);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
}
