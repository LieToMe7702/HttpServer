package com.httpServer;

import com.session.HttpSessionRunner;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner extends AbstractServerRunner {

    protected ServerRunner(ServerSocket socket) {
        super(socket);
    }

    @Override
    protected void dealSocket(Socket socket) {

        HttpSessionRunner.defaultRunSession(socket);

    }


}
