package com.httpServer;

import com.httpFunc.HttpFuncFactory;

public class HttpRequestParser implements IParser {

    private String CRLF = "\r\n";
    private String EMPTY = " ";

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

    public void handleFirstLine(String line) {
        var strs = line.split(EMPTY);
        var funcName = strs[0];
        var url = strs[1];
        var version = strs[2];
        handleFunc(funcName, url, version);
    }

    public void handleHeader(String header) {

    }

    private void handleFunc(String funcName, String url, String version) {
        HttpFuncFactory.GetInstance().Handle(funcName, url, version);
    }
}
