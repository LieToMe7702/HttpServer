package com.struct;

import com.httpFunc.HttpFuncFactory;
import com.httpFuncController.HttpFuncControllerManager;
import com.parser.HttpRequestParser;
import com.parser.HttpResponseParser;
import com.parser.IParser;
import com.parser.IResponseParser;
import com.resource.ResourceManager;

public class Contexts implements IContexts {
    private ResourceManager resourceManager;
    private HttpFuncControllerManager funcControllerManager;
    private HttpFuncFactory httpFuncFactory;

    private IParser requestParser;
    private IResponseParser responseParser;
    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public HttpFuncControllerManager getFuncControllerManager() {
        return funcControllerManager;
    }

    public HttpFuncFactory getHttpFuncFactory() {
        return httpFuncFactory;
    }

    public Contexts() {
        resourceManager = new ResourceManager(this);
        resourceManager = new ResourceManager(this);
        requestParser = new HttpRequestParser(this);
        responseParser = new HttpResponseParser(this);
        funcControllerManager = new HttpFuncControllerManager(this);
        httpFuncFactory = new HttpFuncFactory(this);
    }

    public IParser getRequestParser() {
        return requestParser;
    }

    public IResponseParser getResponseParser() {
        return responseParser;
    }
}
