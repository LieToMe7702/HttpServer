package com.httpFuncController;

import com.httpFunc.HttpFuncEnum;
import com.redis.RedisManager;
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
        var res = RedisManager.getCacheByAddress(url);
        var needUpdate = res == null || res.isEmpty();
        Response response = new Response();

        if(!needUpdate) {
            response.setContent(res);
            response.setStatus(HttpStatus.GetStatusByStatus(200));
            return response;
        }

        var file = resourceManager.getResource(url);
        try {
            var inputStream = new FileInputStream(file);
            var len = inputStream.available();
            var buffer = new BufferedInputStream(inputStream).readNBytes(len);
            var str = new String(buffer);
            response.setContent(str);
            response.setStatus(HttpStatus.GetStatusByStatus(200));
            RedisManager.setCacheByAddress(str,url);
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
