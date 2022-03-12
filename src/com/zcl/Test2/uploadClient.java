package com.zcl.Test2;

import java.io.*;
import java.net.Socket;

/**
 * 上传文件的客户端
 */
public class uploadClient {
    public static void main(String[] args) throws Exception {
        System.out.println("开始时传输文件");
        // 创建一个本地的输入流
        BufferedInputStream buf = new BufferedInputStream(new FileInputStream("Test\\src\\12.text"));
        // 获取网络输出流
        Socket socket = new Socket("localhost",6100);
        // 高效的网络输出流
        BufferedOutputStream bop = new BufferedOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[1024 * 8];
        int leg;
        while ((leg = buf.read(bytes)) != -1) {
            bop.write(bytes, 0, leg);
            bop.flush(); // 刷新
        }
        // 关闭输出流并通知服务端文件以上传完毕
        socket.getOutputStream();
        // 接受服务端反馈的学习
        InputStream inputStream = socket.getInputStream();
        // 接受服务端响应信息
        byte[] b1 = new byte[1024];
        inputStream.read(b1);
        System.out.println(new String(b1));
        inputStream.close();

        socket.close();
        buf.close();
        bop.close();
        System.out.println("文件传输成功");
    }
}
