package com.httpFunc;

import com.struct.AbstractManager;
import com.struct.Contexts;

import java.util.HashMap;

public class HttpFuncFactory extends AbstractManager {

    public void Handle(String name, String url, String version) {
        var func = funcMap.get(name);
        if (func != null) {
            func.handle(url, version);
        }
    }

    public HttpFuncFactory(Contexts newContexts) {
        super(newContexts);
        RegisterFuncHandler();
    }

    private void RegisterFuncHandler() {
        RegisterFuncHandler("GET", new GetFuncHandler(getContexts()));
    }

    private void RegisterFuncHandler(String name, AbstractHttpFuncHandler handler) {
        funcMap.putIfAbsent(name, handler);
    }

    private HashMap<String, AbstractHttpFuncHandler> funcMap = new HashMap<String, AbstractHttpFuncHandler>();
}
