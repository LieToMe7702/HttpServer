package com.httpServer;

public class App {

    private static int port = 8080;

    public static void main(String[] args) {

        IServer server = new SimpleServer();
        server.run(port,args);
        System.out.println("Hello world!");

    }

}

