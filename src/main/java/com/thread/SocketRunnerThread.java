package com.thread;

import com.session.HttpSessionRunner;

import java.net.Socket;

public class SocketRunnerThread implements Runnable {
    @Override
    public void run() {
        HttpSessionRunner.defaultRunSession(mySocket);
    }

    private Socket mySocket;

    public SocketRunnerThread(Socket socket) {
        this.mySocket = socket;
    }
}
