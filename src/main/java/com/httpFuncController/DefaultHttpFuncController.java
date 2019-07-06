package com.httpFuncController;

import com.httpFunc.HttpFuncEnum;
import com.resource.ResourceManager;
import com.struct.Contexts;

import java.io.*;
import java.util.Map;

public class DefaultHttpFuncController extends AbstractHttpFuncController {
    protected DefaultHttpFuncController(String newUrl, Contexts newContexts, HttpFuncControllerManager newManager) {
        super(newUrl, newContexts, newManager);
    }

    @Override
    public HttpFuncEnum getFuncName() {
        return null;
    }

    @Override
    protected void handle(Map<String, String> paraDict) {
        var file = contexts.getResourceManager().getResource(url);
        try {
            var inputStream = new FileInputStream(file);
            var len = inputStream.available();
            var buffer = new BufferedInputStream(inputStream).readNBytes(len);
            var str = new String(buffer);
            contexts.getResponseParser().parse(str);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
