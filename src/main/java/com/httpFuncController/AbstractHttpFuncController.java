package com.httpFuncController;

import com.httpFunc.HttpFuncEnum;
import com.struct.Contexts;

import java.util.Map;

public abstract class AbstractHttpFuncController {
    public abstract HttpFuncEnum getFuncName();

    public String getUrl() {
        return this.url;
    }

    protected abstract void handle(Map<String, String> paraDict);

    protected void registerController() {
        manager.registerController(getFuncName(), this);
    }

    protected String url;
    protected HttpFuncControllerManager manager;
    public void setUrl(String url) {
        this.url = url;
    }
    protected Contexts contexts;
    protected AbstractHttpFuncController(String newUrl,Contexts newContexts,HttpFuncControllerManager newManager) {
        contexts = newContexts;
        manager = newManager;
        setUrl(newUrl);
        registerController();
    }

}
