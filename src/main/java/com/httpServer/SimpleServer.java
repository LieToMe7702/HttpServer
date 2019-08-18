package com.httpServer;

import java.io.IOException;
import java.net.ServerSocket;

public class SimpleServer extends AbstractServer {

    @Override
    public void run(int port, String[] args) {

        try {
            var server = new ServerSocket(port);
            var serverRunner = new MultiThreadingServerRunner(server);
            serverRunner.exec();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

