package com.httpServer;

import java.net.ServerSocket;

public class App {

    private static int port = 8080;

    public static void main(String args[]) {

        IServer server = new SimpleServer();
        server.run(port);
        System.out.println("Hello world!");

    }

    /*private static void CreateSocket() {
        ServerSocket socket = new ServerSocket(port);
    }*/
}

