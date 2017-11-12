package com.lin.socket.client;

import javax.swing.text.html.ObjectView;
import java.io.*;
import java.net.Socket;

/**
 * Created by lin on 2017/11/11.
 */
public class SocketClient {

    public static void main(String arge[]) throws IOException {

        Socket socket=new Socket("localhost",8989);
        OutputStream os=socket.getOutputStream();
        InputStream is=socket.getInputStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        PrintWriter pr=new PrintWriter(os);
        pr.println("hello java!");
        pr.flush();
        String resource=br.readLine(); //这是一个阻塞方法，会一直等待服务器端的回复，只有收到服务器端的回复才会继续往下执行
        System.out.println("Client:"+resource);

        pr.close();
        br.close();
        os.close();
        is.close();
        socket.close();
    }
}
