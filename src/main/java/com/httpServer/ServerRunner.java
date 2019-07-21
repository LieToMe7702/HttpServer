package com.httpServer;

import com.session.HttpSession;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerRunner {
    private ServerSocket mySocket;
    public ServerRunner(ServerSocket socket)
    {
        mySocket = socket;
    }
    public void exec(){
        while (!mySocket.isClosed()) {
            try {
                var socket = mySocket.accept();
                var inputStream = socket.getInputStream();
                var outputStream = socket.getOutputStream();
                var httpSession = new HttpSession(inputStream, outputStream,socket);
                while(!socket.isClosed()) {
                    httpSession.exec();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
