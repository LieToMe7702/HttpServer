package com.httpFunc;

public class GetFuncHandler extends AbstractHttpFuncHandler {
    @Override
    public HttpFuncEnum getFuncName() {
        return HttpFuncEnum.GET;
    }

    @Override
    public void handle(String url, String version) {
        System.out.println("GetFuncHandler");
        System.out.println(url);
        System.out.println(version);
        System.out.println("GetFuncHandler");
    }
}
