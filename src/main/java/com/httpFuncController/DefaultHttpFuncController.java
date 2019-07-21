package com.httpFuncController;

import com.httpFunc.HttpFuncEnum;
import com.resource.HttpStatus;
import com.resource.ResourceManager;
import com.response.Response;

import java.io.*;
import java.util.Map;

public class DefaultHttpFuncController extends AbstractHttpFuncController {
    protected DefaultHttpFuncController(String newUrl,HttpFuncControllerManager newManager) {
        super(newUrl, newManager);
        resourceManager = new ResourceManager();
    }

    private ResourceManager resourceManager;
    @Override
    public HttpFuncEnum getFuncName() {
        return null;
    }

    @Override
    protected Response handle(Map<String, String> paraDict) {
        var file = resourceManager.getResource(url);
        Response response = new Response();
        try {
            var inputStream = new FileInputStream(file);
            var len = inputStream.available();
            var buffer = new BufferedInputStream(inputStream).readNBytes(len);
            var str = new String(buffer);
            response.setContent(str);
            response.setStatus(HttpStatus.GetStatusByStatus(200));
            return response;


        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            response.setStatus(HttpStatus.GetStatusByStatus(404));
            System.out.println(e);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

}
