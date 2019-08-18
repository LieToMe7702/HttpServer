package com.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketRunnerThreadPool {
    private ThreadPoolExecutor executor;
    private ThreadFactory nameFactory;

    public SocketRunnerThreadPool() {
        nameFactory = new ThreadFactoryBuilder().setNameFormat("ThreadPool-%d").build();
        executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5), nameFactory);

    }

    public void exec(Socket socket) {
        executor.execute(new SocketRunnerThread(socket));
    }
}
