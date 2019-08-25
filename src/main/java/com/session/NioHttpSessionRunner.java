package com.session;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioHttpSessionRunner {
    public static void defaultRunSession(SocketChannel socketChannel, ByteBuffer byteBuffer,int readNum){
        var handler = new NioHttpSessionHandler();
        handler.exec(socketChannel,byteBuffer,readNum);
    }
}
