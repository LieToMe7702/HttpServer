package com.httpFuncController;

import com.httpFunc.HttpFuncEnum;

import java.util.Map;

public abstract class AbstractHttpFuncController {
    public abstract HttpFuncEnum getFuncName();
    public abstract String getUrl();
    protected AbstractHttpFuncController()
    {
        registerController();
    }

    protected abstract void Handle(Map<String, String> paraDict);

    protected void registerController()
    {
        HttpFuncControllerManager.GetInstance().registerController(getFuncName(), this);
    }
}
