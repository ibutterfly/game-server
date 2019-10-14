package com.butterfly.game.netty.message;

/**
 * Created by 杨国 on 2019/10/14.
 * 自定义协议头
 * @author yangguo
 */
public class Header {
    //标识
    private byte tag;
    //编码
    private byte encode;
    //加密
    private byte encrypt;
    //其他字段1
    private byte extend1;
    //其他字段2
    private byte extend2;
    //会话id
    private String sessionid;
    //包长度
    private int length;
    //指令
    private int command;

    public Header(){

    }

    public Header(byte tag, byte encode, byte encrypt, byte extend1, byte extend2, String sessionid, int length, int command) {
        this.tag = tag;
        this.encode = encode;
        this.encrypt = encrypt;
        this.extend1 = extend1;
        this.extend2 = extend2;
        this.sessionid = sessionid;
        this.length = length;
        this.command = command;
    }

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public byte getEncode() {
        return encode;
    }

    public void setEncode(byte encode) {
        this.encode = encode;
    }

    public byte getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(byte encrypt) {
        this.encrypt = encrypt;
    }

    public byte getExtend1() {
        return extend1;
    }

    public void setExtend1(byte extend1) {
        this.extend1 = extend1;
    }

    public byte getExtend2() {
        return extend2;
    }

    public void setExtend2(byte extend2) {
        this.extend2 = extend2;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }
}
