package com.httpFuncController;

import com.httpFunc.HttpFuncEnum;
import com.response.Response;

import java.util.Map;

public abstract class AbstractHttpFuncController {
    public abstract HttpFuncEnum getFuncName();

    public String getUrl() {
        return this.url;
    }

    protected abstract Response handle(Map<String, String> paraDict);

    protected void registerController() {
        manager.registerController(getFuncName(), this);
    }

    protected String url;
    protected HttpFuncControllerManager manager;
    public void setUrl(String url) {
        this.url = url;
    }
    protected AbstractHttpFuncController(String newUrl,HttpFuncControllerManager newManager) {
        manager = newManager;
        setUrl(newUrl);
        registerController();
    }

}
