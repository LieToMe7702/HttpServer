package com.parser;

import com.httpFunc.HttpFuncFactory;
import com.struct.AbstractManager;
import com.struct.Contexts;

public class HttpRequestParser extends AbstractManager implements IParser {

    private String CRLF = "\r\n";
    private String EMPTY = " ";

    public HttpRequestParser(Contexts newContexts) {
        super(newContexts);
    }

    @Override
    public void parse(String content) {

        var index = 1;
        var strs = content.split(CRLF);
        var RequestLine = strs[0];
        handleFirstLine(RequestLine);
        for (; index < strs.length; index++) {
            var header = strs[index];
            if (header.equals(CRLF)) {
                break;
            }
            handleHeader(header);
        }
        for (var str : strs
        ) {
            System.out.println(str);
        }
    }

    @Override
    public void handleFirstLine(String line) {
        var strs = line.split(EMPTY);
        var funcName = strs[0];
        var url = strs[1];
        var version = strs[2];
        handleFunc(funcName, url, version);
    }

    @Override
    public void handleHeader(String header) {

    }

    private void handleFunc(String funcName, String url, String version) {
        getContexts().getHttpFuncFactory().Handle(funcName, url, version);
    }
}
