package com.httpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class AbstractServerRunner {
    protected ServerSocket mySocket;

    protected AbstractServerRunner(ServerSocket socket) {
        mySocket = socket;
    }

    public void exec() {
        while (!mySocket.isClosed()) {
            try {
                var socket = mySocket.accept();
                dealSocket(socket);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    protected abstract void dealSocket(Socket socket);

}
