package com.zcl.BSTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("客户端启动");
        // 创建字节套接字
        ServerSocket serverSocket = new ServerSocket(8081);
        // 监听客户端套接字
        Socket accept = serverSocket.accept();
        // 获取客户端发送的请求
        /*BufferedInputStream bui = new BufferedInputStream(accept.getInputStream());
        byte[] b = new byte[1024];
        int len;
        while ((len = bui.read(b))!= -1){
            System.out.println(new String(b,0,len));
        }*/

        BufferedReader buf = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        String str = buf.readLine();// 读取第一行数字据
        System.out.println(str);

    }
}
