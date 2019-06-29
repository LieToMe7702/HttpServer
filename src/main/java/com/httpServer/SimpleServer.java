package com.httpServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class SimpleServer implements IServer {

    public void run(int port) {


        try {
            var server = new ServerSocket(port);
            while(true)
            {
                var socket = server.accept();
                var inputStream = socket.getInputStream();
                var data = new BufferedInputStream(inputStream);
                var content = data.readAllBytes();
                var str = new String(content, "utf-8");
                System.out.println(str);
                var outputStream =  socket.getOutputStream();
                var responseStr = "HTTP/1.0 404 Not found\r\n\r\n" ;
                var bytes = responseStr.getBytes("utf-8");
                var outData = new BufferedOutputStream(outputStream);
                outData.write(bytes);
                outData.flush();

                inputStream.close();
                outputStream.close();
                socket.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
