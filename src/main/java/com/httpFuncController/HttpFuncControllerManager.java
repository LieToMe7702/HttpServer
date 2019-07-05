package com.httpFuncController;

import com.httpFunc.HttpFuncEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpFuncControllerManager {
    private static HttpFuncControllerManager instance;

    private Map<HttpFuncEnum, List<AbstractHttpFuncController>> controllerMap = new HashMap<>();

    public static HttpFuncControllerManager GetInstance() {
        if (instance == null) instance = new HttpFuncControllerManager();
        return instance;
    }

    public void registerController(HttpFuncEnum funcName, AbstractHttpFuncController abstractHttpFuncController) {
        if (!controllerMap.containsKey(funcName)) {
            controllerMap.put(funcName, new ArrayList<>());
        }
        controllerMap.get(funcName).add(abstractHttpFuncController);
    }

    public void handleController(HttpFuncEnum funcName, String url, Map<String, String> paraDict) {
        var controllerList = controllerMap.get(funcName);
        if(controllerList == null) return;
        for (var it: controllerList
             ) {
            if(it.getUrl() == url)
            {
                it.Handle(paraDict);
            }
        }
    }
}
