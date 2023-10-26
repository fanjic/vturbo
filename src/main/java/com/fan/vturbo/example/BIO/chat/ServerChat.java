package com.fan.vturbo.example.BIO.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerChat {

    public static List<Socket> allSocketOnline=new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket ss=new ServerSocket(9999);
            while (true){
                Socket socket= ss.accept();
                allSocketOnline.add(socket);
                new ServerChatReaderThread(socket).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
