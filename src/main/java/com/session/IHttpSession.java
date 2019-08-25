package com.session;

public interface IHttpSession {
    
    void setHeaderInfo(String funcName, String url, String version);

    String getFuncName();

    String getUrl();

    String getVersion();
}
