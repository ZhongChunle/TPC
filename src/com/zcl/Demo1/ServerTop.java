package com.zcl.Demo1;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTop {
    public static void main(String[] args) throws Exception {
        System.out.println("服务端启动");
        // 创建套接字对象
        ServerSocket serverSocket = new ServerSocket(6000);
        while (true) {
            // 监听客户端的连接，返回客户端的套接字
            Socket accept = serverSocket.accept();
            // 来一个客户端开启一个线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(System.currentTimeMillis() + ".text"));
                        // 获取客户端套接字的输入流
                        BufferedInputStream inputStream = new BufferedInputStream(accept.getInputStream());
                        // 接受数据
                        byte[] b = new byte[1024 * 8];
                        int len;
                        while ((len = inputStream.read(b)) != -1) {
                            bos.write(b, 0, len);
                        }
                        // 获取网络输出流
                        OutputStream outputStream = accept.getOutputStream();
                        // 先客户端输出
                        outputStream.write("文件上传成功".getBytes());
                        outputStream.close();

                        /*
                            服务端启动
                            Hello World
                         */
                        bos.close();
                        accept.close();
                        // serverSocket.close();
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
