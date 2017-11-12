package com.lin.socket.server;

import java.io.*;
import java.net.Socket;

/**
 * Created by lin on 2017/11/11.
 */
class ServerTread  extends Thread{
    Socket socket=null;

    public ServerTread(){}
    public ServerTread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        InputStream is=null;
        BufferedReader bfr=null;
        OutputStream os=null;
        PrintWriter pr=null;
        try{
            is=socket.getInputStream();
            bfr=new BufferedReader(new InputStreamReader(is));
            os =socket.getOutputStream();
            pr=new PrintWriter(os);
            String resource=bfr.readLine();
            System.out.println("server:"+resource);
            pr.println(resource+"okok");
            pr.flush();
        }catch (Exception e){
            System.out.print(e);
        }finally {
            try {
                pr.close();
                os.close();
                bfr.close();
                is.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
