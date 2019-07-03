package com.httpFunc;

public abstract class AbstractHttpFuncHandler {
    public abstract HttpFuncEnum getFuncName();
    public abstract void handle(String url, String version);
}
