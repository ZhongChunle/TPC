package com.zcl.Demo1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTcp {
    public static void main(String[] args) throws Exception {
        System.out.println("客户端启动了");
        // 创建套接字对象
        Socket socket = new Socket("localhost",6000);
        // 获取到套接字对象的输出流
        OutputStream out = socket.getOutputStream();
        // 服务端输出数据
        out.write("Hello World".getBytes());
        socket.close();
    }
}
