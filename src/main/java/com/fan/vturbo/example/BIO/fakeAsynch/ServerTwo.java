package com.fan.vturbo.example.BIO.fakeAsynch;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerTwo {

    public static void main(String[] args) {
        try {
            System.out.println("服务端的小明启动");
            ServerSocket ss = new ServerSocket(9999);

            HandlerServerSocketPool pool=new HandlerServerSocketPool(2,3);
            while (true) {
                Socket socket= ss.accept();
                Runnable target=new ServerRunnableTarget(socket);
                pool.execute(target);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
