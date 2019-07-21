package com.parser;

public class HttpResponseParser implements IResponseParser {

    @Override
    public void parse(String newContent) {
        content = newContent;
    }

    @Override
    public void handleHeader(String header) {

    }

    @Override
    public void handleFirstLine(String line) {

    }

    private String content;
    @Override
    public String getContent() {
        return content;
    }
}
