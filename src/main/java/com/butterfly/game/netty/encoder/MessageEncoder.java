package com.butterfly.game.netty.encoder;

import com.butterfly.game.netty.decoder.MessageDecoder;
import com.butterfly.game.netty.message.Header;
import com.butterfly.game.netty.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by 杨国 on 2019/10/14.
 *
 * @author yangguo
 */
public class MessageEncoder extends MessageToByteEncoder<Message>{
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        Header header = msg.getHeader();
        out.writeByte(MessageDecoder.PACKAGE_TAG);
        out.writeByte(header.getEncode());
        out.writeByte(header.getEncrypt());
        out.writeByte(header.getExtend1());
        out.writeByte(header.getExtend2());
        out.writeBytes(header.getSessionid().getBytes());
        out.writeInt(header.getLength());
        out.writeInt(header.getCommand());
        out.writeBytes(msg.getData().getBytes("UTF-8"));
    }
}
