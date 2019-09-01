package com.httpServer;

import com.redis.RedisManager;

public class App {

    private static int port = 8080;

    public static void main(String[] args) {
        RedisManager.getJedis().set("name","httpRedis");
        IServer server = new NioServer();
        server.run(port,args);
        System.out.println("Hello world!");

    }

}

