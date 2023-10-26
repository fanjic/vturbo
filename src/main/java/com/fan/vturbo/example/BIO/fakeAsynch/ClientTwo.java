package com.fan.vturbo.example.BIO.fakeAsynch;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTwo {

    public static void main(String[] args) {
        try {
            Socket socket=new Socket("127.0.0.1",9999);
            OutputStream os= socket.getOutputStream();
            PrintStream ps=new PrintStream(os);

            Scanner sc=new Scanner(System.in);
            while (true){
                System.out.println("小雯说：");
                String msg=sc.nextLine();
                ps.println(msg);
                ps.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
