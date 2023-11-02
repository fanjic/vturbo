package com.fan.vturbo.example.BIO.oneThread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientBio {

    public static void main(String[] args) throws IOException {

        Socket socket=new Socket("127.0.0.1",9999);
        OutputStream os= socket.getOutputStream();
        PrintStream ps=new PrintStream(os);

        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("小雯发送：");
            String msg=sc.nextLine();
            ps.println(msg);
            ps.flush();
        }



    }


}
