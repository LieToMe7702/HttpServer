package com.httpFunc;

import com.struct.Contexts;
import com.struct.KeyValuePair;

public class GetFuncHandler extends AbstractHttpFuncHandler {
    protected GetFuncHandler(Contexts newContexts) {
        super(newContexts);
    }

    @Override
    public HttpFuncEnum getFuncName() {
        return HttpFuncEnum.GET;
    }

    private final String ParmPrefix = "\\?";
    private final String ParmSplit = "&";
    private final String KeyValueSplit = "=";

    @Override
    public void parse(String url, String version) {

        parseUrl(url);

        System.out.println("GetFuncHandler");
        System.out.println(url);
        System.out.println(version);
        System.out.println("GetFuncHandler");
    }

    private void parseUrl(String url) {

        var strs = url.split(ParmPrefix);
        var locate = strs[0];
        requestUrl = locate;

        if (strs.length <= 1) return;

        parseParas(strs[1]);
    }


    private void parseParas(String str) {
        var paraStr = str;
        var paras = paraStr.split(ParmSplit);


        paraDict.clear();
        for (var para : paras
        ) {
            var keyValue = parseKeyValuePara(para);
            paraDict.putIfAbsent(keyValue.getKey(), keyValue.getValue());
        }
    }

    private KeyValuePair parseKeyValuePara(String para) {
        var paras = para.split(KeyValueSplit);
        var res = new KeyValuePair(paras[0], paras[1]);
        return res;
    }


}
