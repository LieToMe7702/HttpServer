package com.parser;

import com.struct.AbstractManager;
import com.struct.Contexts;

public class HttpResponseParser extends AbstractManager implements IResponseParser {
    public HttpResponseParser(Contexts newContexts) {
        super(newContexts);
    }

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
