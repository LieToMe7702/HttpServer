package com.httpFuncController;

import com.httpFunc.HttpFuncEnum;
import com.struct.AbstractManager;
import com.struct.Contexts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpFuncControllerManager extends AbstractManager {
    private AbstractHttpFuncController defaultController;
    private Map<HttpFuncEnum, List<AbstractHttpFuncController>> controllerMap = new HashMap<>();

    public HttpFuncControllerManager(Contexts newContexts) {
        super(newContexts);
        defaultController = new DefaultHttpFuncController("",newContexts,this);
    }


    public void registerController(HttpFuncEnum funcName, AbstractHttpFuncController abstractHttpFuncController) {
        if (!controllerMap.containsKey(funcName)) {
            controllerMap.put(funcName, new ArrayList<>());
        }
        controllerMap.get(funcName).add(abstractHttpFuncController);
    }

    public void handleController(HttpFuncEnum funcName, String url, Map<String, String> paraDict) {
        var controllerList = controllerMap.get(funcName);
        if (controllerList != null) {
            for (var it : controllerList
            ) {
                if (it.getUrl() == url) {
                    it.handle(paraDict);
                    return;
                }
            }
        }

        defaultController.setUrl(url);
        defaultController.handle(paraDict);


    }
}
