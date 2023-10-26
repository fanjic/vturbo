package com.fan.vturbo.example.BIO.file;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

public class ServerReaderThread extends Thread {

    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String suffix = dis.readUTF();
            System.out.println("服务端接收到的后缀："+suffix);
            OutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\copynew\\"
                    + UUID.randomUUID().toString() + suffix);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = dis.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            os.close();
            System.out.println("文件接收成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
