package com.httpServer;

public interface IParser {

    void parse(String content);

    void handleHeader(String header);

    void handleFirstLine(String line);
}

