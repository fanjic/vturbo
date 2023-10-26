package com.fan.vturbo.example.BIO.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerChatReaderThread extends Thread {

    private Socket socket;

    public ServerChatReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while ((msg = br.readLine()) != null) {
                sendMsgToAllClient(msg);
                System.out.println(msg);
            }

        } catch (IOException e) {
            System.out.println("当前有人下线了");
            ServerChat.allSocketOnline.remove(socket);
            e.printStackTrace();
        }
    }

    public  void sendMsgToAllClient(String msg) throws IOException {

        for (Socket sk : ServerChat.allSocketOnline) {
            PrintStream ps=new PrintStream(sk.getOutputStream());
            ps.println(msg);
            ps.flush();
        }
    }
}
