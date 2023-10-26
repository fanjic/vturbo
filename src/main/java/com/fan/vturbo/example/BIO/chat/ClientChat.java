package com.fan.vturbo.example.BIO.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {

    public static void main(String[] args) {
        try {
            // 输出流发送消息
            Socket socket=new Socket("127.0.0.1",9999);
            OutputStream os= socket.getOutputStream();
            PrintStream ps=new PrintStream(os);

            // 输入流接收消息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner sc=new Scanner(System.in);
            while (true){
                // 发送消息
                System.out.println("我说：");
                String msg=sc.nextLine();
                ps.println(msg);
                ps.flush();

                // 接收消息
                String getMsg;
                while ((getMsg = br.readLine()) != null) {
                    System.out.println("我收到：" + msg);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
