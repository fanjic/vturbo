package com.fan.vturbo.example.BIO.oneThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBio {

    public static void main(String[] args) {
        try {
            System.out.println("服务端的小明启动");
            ServerSocket ss = new ServerSocket(9999);
            Socket socket = ss.accept();
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println("小明收到："+msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
