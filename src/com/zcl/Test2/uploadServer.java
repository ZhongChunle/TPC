package com.zcl.Test2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收上传文件的服务端
 */
public class uploadServer {
    public static void main(String[] args) throws Exception {
        System.out.println("服务端启动");
        // 创建一个本地输出流，保存文件到本地
        FileOutputStream fo = new FileOutputStream("copy.text");
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 高效封装输出流
                        BufferedOutputStream bf = new BufferedOutputStream(fo); // 可以将上面的代码整合一起
                        // 获取网络输入流
                        ServerSocket serverSocket = new ServerSocket(6100);
                        // 监听客户端
                        Socket accept = serverSocket.accept(); // 返回监听到的客户端套接字
                        // 获取客户端的输入流
                        // 封装成高效的输入流
                        BufferedInputStream inputStream1 = new BufferedInputStream(accept.getInputStream());
                        // 定义字节数组储存数据
                        byte[] b = new byte[1024];
                        int len;
                        while ((len = inputStream1.read(b))!=-1){
                            // 输出到磁盘
                            bf.write(b);
                        }
                        // 获取网络输出流
                        OutputStream outputStream = accept.getOutputStream();
                        // 先客户端输出
                        outputStream.write("文件上传成功".getBytes());
                        outputStream.close();

                        bf.close();
                        inputStream1.close();
                        serverSocket.close();
                        accept.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
