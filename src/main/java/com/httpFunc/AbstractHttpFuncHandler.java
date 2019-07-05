package com.httpFunc;

import com.httpFuncController.HttpFuncControllerManager;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHttpFuncHandler {
    public abstract HttpFuncEnum getFuncName();
    public void handle(String url, String version){
        parse(url,version);
        HttpFuncControllerManager.GetInstance().handleController(getFuncName(),url,paraDict);
    }
    public abstract void parse(String url, String version);
    protected Map<String, String> paraDict = new HashMap<>();
    protected String requestUrl = "";

}
