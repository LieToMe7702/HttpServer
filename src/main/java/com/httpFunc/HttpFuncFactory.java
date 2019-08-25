package com.httpFunc;

import com.response.Response;
import com.session.IHttpSession;

import java.util.HashMap;

public class HttpFuncFactory{

    private static HttpFuncFactory instance;
    public static HttpFuncFactory GetInstane(){
        if(instance == null){
            instance = new HttpFuncFactory();
        }
        return instance;
    }
    public Response Handle(String name, String url, String version) {
        var func = funcMap.get(name);
        if (func != null) {
            return func.handle(url, version);
        }
        return new Response();
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

    public Response Handle(IHttpSession httpSession) {
        var response = Handle(httpSession.getFuncName(),httpSession.getUrl(),httpSession.getVersion());
        response.setFuncName(httpSession.getFuncName());
        return response;
    }
}
