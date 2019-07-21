package com.httpFuncController;

import com.httpFunc.HttpFuncEnum;
import com.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpFuncControllerManager{
    private AbstractHttpFuncController defaultController;
    private Map<HttpFuncEnum, List<AbstractHttpFuncController>> controllerMap = new HashMap<>();

    private static HttpFuncControllerManager instance;
    public static HttpFuncControllerManager getInstance(){
        if(instance == null){
            instance = new HttpFuncControllerManager();
        }
        return instance;
    }

    private HttpFuncControllerManager() {
        defaultController = new DefaultHttpFuncController("",this);
    }


    public void registerController(HttpFuncEnum funcName, AbstractHttpFuncController abstractHttpFuncController) {
        if (!controllerMap.containsKey(funcName)) {
            controllerMap.put(funcName, new ArrayList<>());
        }
        controllerMap.get(funcName).add(abstractHttpFuncController);
    }

    public Response handleController(HttpFuncEnum funcName, String url, Map<String, String> paraDict) {
        var controllerList = controllerMap.get(funcName);
        if (controllerList != null) {
            for (var it : controllerList
            ) {
                if (it.getUrl() == url) {
                    return it.handle(paraDict);
                }
            }
        }

        defaultController.setUrl(url);
        return defaultController.handle(paraDict);


    }
}
