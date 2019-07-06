package com.parser;

public interface IParser {

    void parse(String content);

    void handleHeader(String header);

    void handleFirstLine(String line);
}

