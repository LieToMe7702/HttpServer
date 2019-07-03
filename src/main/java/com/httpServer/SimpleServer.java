package com.httpServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;

public class SimpleServer implements IServer {

    IParser parser = new HttpRequestParser();

    @Override
    public void run(int port) {

        try {
            var server = new ServerSocket(port);
            acceptServer(server);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void acceptServer(ServerSocket server) {
        while (true) {
            try {
                var socket = server.accept();
                var inputStream = socket.getInputStream();
                var len = inputStream.available();
                var buffer = new BufferedInputStream(inputStream).readNBytes(len);
                var str = new String(buffer);
                parser.parse(str);
                System.out.println(str);
                var outputStream = socket.getOutputStream();
                var responseStr = "HTTP/1.0 404 Not found\r\n\r\n";
                var bytes = responseStr.getBytes(StandardCharsets.UTF_8);
                var outData = new BufferedOutputStream(outputStream);
                outData.write(bytes);
                outData.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
