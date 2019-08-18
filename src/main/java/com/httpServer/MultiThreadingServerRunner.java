package com.httpServer;

import com.thread.SocketRunnerThreadPool;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadingServerRunner extends AbstractServerRunner {
    private SocketRunnerThreadPool threadPool;

    protected MultiThreadingServerRunner(ServerSocket socket) {
        super(socket);
        threadPool = new SocketRunnerThreadPool();
    }

    @Override
    protected void dealSocket(Socket socket) {
        threadPool.exec(socket);
    }
}
