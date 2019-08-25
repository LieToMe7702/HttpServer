package com.httpServer;

import com.session.NioHttpSessionRunner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServer extends AbstractServer {
    @Override
    public void run(int port, String[] args) {
        try {
            var socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            var socket = socketChannel.socket();
            var address = new InetSocketAddress(port);
            socket.bind(address);
            var selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);

            dealSelector(selector);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dealSelector(Selector selector) {
        while (true) {
            try {
                var num = selector.select();
                if (num <= 0) {
                    continue;
                }
                var selectedKeys = selector.selectedKeys();
                var iterator = selectedKeys.iterator();
                while (iterator.hasNext()) {
                    var it = iterator.next();
                    if ((it.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                        var serverSocketChannel = (ServerSocketChannel) it.channel();
                        var socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        iterator.remove();
                    } else if ((it.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                        read(it);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private void read(SelectionKey key) throws IOException {
        int numRead = 0;
        var socketChannel = (SocketChannel) key.channel();
        byteBuffer.clear();
        try {
            numRead = socketChannel.read(byteBuffer);
            if (numRead == -1 || numRead == 0) {
                socketChannel.close();
                key.cancel();
                return;
            }
            NioHttpSessionRunner.defaultRunSession(socketChannel,byteBuffer,numRead);

        } catch (Exception e) {
            e.printStackTrace();
            socketChannel.close();
            key.cancel();
        }

    }
}
