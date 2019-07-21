package com.parser;

import com.session.IHttpSession;

public class HttpRequestParser implements IParser {

    private final String CRLF = "\r\n";
    private final String EMPTY = " ";
    private final IHttpSession httpSession;


    public HttpRequestParser(IHttpSession session){
        this.httpSession = session;
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
        httpSession.setHeaderInfo(funcName,url,version);
    }

    @Override
    public void handleHeader(String header) {

    }

}
