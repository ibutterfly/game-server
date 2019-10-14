package com.butterfly.game.netty.decoder;

import com.butterfly.game.netty.message.Header;
import com.butterfly.game.netty.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

/**
 * Created by 杨国 on 2019/10/14.
 *
 * @author yangguo
 */
public class MessageDecoder extends ByteToMessageDecoder {
    public static final int HEAD_LENGTH = 45;

    public static final byte PACKAGE_TAG = 0x01;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        in.markReaderIndex();
        if (in.readableBytes() < HEAD_LENGTH) {
            throw new CorruptedFrameException("包长度问题");
        }
        byte tag = in.readByte();
        if (tag != PACKAGE_TAG) {
            throw new CorruptedFrameException("标识问题");
        }
        byte encode = in.readByte();
        byte encrypt = in.readByte();
        byte extend1 = in.readByte();
        byte extend2 = in.readByte();
        byte sessionByte[] = new byte[32];
        in.readBytes(sessionByte);
        String sessionid = new String(sessionByte, "UTF-8");
        int length = in.readInt();
        int command = in.readInt();
        Header header = new Header(tag, encode, encrypt, extend1, extend2, sessionid, length, command);
        byte[] data = new byte[length];

        in.readBytes(data);
        Message message = new Message(header, new String(data, "UTF-8"));
        out.add(message);
    }
}
