package com.httpFunc;

import java.util.HashMap;

public class HttpFuncFactory {

    private static HttpFuncFactory instance;

    public static HttpFuncFactory GetInstance() {
        if (instance == null) instance = new HttpFuncFactory();
        return instance;
    }

    public void Handle(String name, String url, String version) {
        var func = funcMap.get(name);
        if (func != null)
            func.handle(url, version);
    }

    private HttpFuncFactory() {
        RegisterFuncHandler();
    }

    private void RegisterFuncHandler() {
        RegisterFuncHandler("GET", new GetFuncHandler());
    }

    private void RegisterFuncHandler(String name, AbstractHttpFuncHandler handler) {
        funcMap.putIfAbsent(name, handler);
    }

    private HashMap<String, AbstractHttpFuncHandler> funcMap = new HashMap<String, AbstractHttpFuncHandler>();
}
