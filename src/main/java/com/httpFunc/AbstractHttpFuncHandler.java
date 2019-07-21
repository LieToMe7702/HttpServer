package com.httpFunc;

import com.httpFuncController.HttpFuncControllerManager;
import com.response.Response;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHttpFuncHandler {

    public abstract HttpFuncEnum getFuncName();

    public Response handle(String url, String version) {
        parse(url, version);
        return HttpFuncControllerManager.getInstance().handleController(getFuncName(), url, paraDict);
    }

    public abstract void parse(String url, String version);

    protected Map<String, String> paraDict = new HashMap<>();
    protected String requestUrl = "";



}
