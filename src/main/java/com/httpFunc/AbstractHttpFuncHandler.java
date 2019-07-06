package com.httpFunc;

import com.httpFuncController.HttpFuncControllerManager;
import com.struct.Contexts;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHttpFuncHandler {
    protected final Contexts contexts;

    public abstract HttpFuncEnum getFuncName();

    public void handle(String url, String version) {
        parse(url, version);
        contexts.getFuncControllerManager().handleController(getFuncName(), url, paraDict);
    }

    public abstract void parse(String url, String version);

    protected Map<String, String> paraDict = new HashMap<>();
    protected String requestUrl = "";

    protected AbstractHttpFuncHandler(Contexts newContexts)
    {
        this.contexts = newContexts;
    }

}
