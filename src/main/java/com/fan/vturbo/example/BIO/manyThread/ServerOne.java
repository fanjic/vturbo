package com.fan.vturbo.example.BIO.manyThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerOne {

    public static void main(String[] args) {
        try {
            System.out.println("服务端的小明启动");
            ServerSocket ss = new ServerSocket(9999);

            while (true) {
                Socket socket = ss.accept();
                new ServerOneThreadReader(socket).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
