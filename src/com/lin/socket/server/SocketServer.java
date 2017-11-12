package com.lin.socket.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by lin on 2017/11/10.
 */
public class SocketServer {
    private static  int i=1;
    public static void main(String arge[]) throws IOException {

        //socket通信服务器端

        //创建一个socket监听对象
        ServerSocket sc= new ServerSocket();
        sc.bind(new InetSocketAddress(8989));  //绑定到本机的8989端口

        while (true) {
            //接收服务器端的连接请求
            Socket socket = sc.accept();//这是个阻塞方法，一直等待客户端的连接
            System.out.println("获取连接对象"+i++);
            new ServerTread(socket).start();
        }
    }
}
