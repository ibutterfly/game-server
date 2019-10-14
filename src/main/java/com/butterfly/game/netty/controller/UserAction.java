package com.butterfly.game.netty.controller;

import com.butterfly.game.netty.core.ActionMap;
import com.butterfly.game.netty.core.NettyController;
import com.butterfly.game.netty.message.Message;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by 杨国 on 2019/10/14.
 *
 * @author yangguo
 */
@NettyController
public class UserAction {
    @ActionMap(key = 1)
    public String login(ChannelHandlerContext ct, Message message){
        System.out.println(message.getData());
        return null;
    }
}
