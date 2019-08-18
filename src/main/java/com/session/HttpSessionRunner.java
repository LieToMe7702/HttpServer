package com.session;

import java.io.IOException;
import java.net.Socket;

public class HttpSessionRunner {
    public static void defaultRunSession(Socket socket) {
        try {
            var inputStream = socket.getInputStream();
            var outputStream = socket.getOutputStream();
            var httpSession = new HttpSession(inputStream, outputStream, socket);
            while (!socket.isClosed()) {
                httpSession.exec();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
