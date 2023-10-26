package com.fan.vturbo.example.BIO.file;

import java.io.*;
import java.net.Socket;

public class ClientFile {

    public static void main(String[] args) {

        String suffix = ".txt";
        File file = new File("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\10.jpg");
        if(file.exists()){
            System.out.println(file.getName());
            String fileName= file.getName();
            suffix=fileName.substring(fileName.lastIndexOf("."));
            System.out.println("客户端截取的文件后缀："+suffix);
        }

        try (
                InputStream is = new FileInputStream(file);

        ) {
            Socket socket = new Socket("127.0.0.1", 8888);
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            System.out.println("文件开始发送");
            dos.writeUTF(suffix);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                dos.write(buffer, 0, len);
            }
            dos.flush();
            socket.shutdownOutput();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
