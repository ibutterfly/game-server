package com.butterfly.game.netty;

import com.butterfly.game.netty.message.Header;
import com.butterfly.game.netty.message.Message;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 杨国 on 2019/10/14.
 *
 * @author yangguo
 */
public class TestClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        try {
            OutputStream out = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            while(true){
                String send = scanner.nextLine();
                System.out.println("客户端"+send);
                byte[] by = send.getBytes("UTF-8");
                Header header = new Header((byte)1,(byte)1,(byte)1,(byte)1,(byte)1,"713f17ca614361fb257dc6741332caf2",by.length,1);
                Message message = new Message(header,send);
                out.write(message.toByte());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
