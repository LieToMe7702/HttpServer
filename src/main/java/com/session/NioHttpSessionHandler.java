package com.session;

import com.httpFunc.HttpFuncFactory;
import com.parser.HttpRequestParser;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioHttpSessionHandler implements IHttpSession{
   public void exec(SocketChannel socketChannel, ByteBuffer byteBuffer, int readNum){

       var buffer = byteBuffer.array();
       byte[] dataCopy = new byte[readNum];

       System.arraycopy(buffer, 0, dataCopy, 0, readNum);
       var inputStr = new String(dataCopy);

       var httpRequestParser = new HttpRequestParser(this);
       httpRequestParser.parse(inputStr);
       var response = HttpFuncFactory.GetInstane().Handle(this);
       if(response != null){
           response.send(socketChannel);
       }
   }

    String funcName;
    String url;
    String version;
    @Override
    public void setHeaderInfo(String funcName, String url, String version) {
        this.funcName = funcName;
        this.url = url;
        this.version = version;
    }

    @Override
    public String getFuncName() {
        return funcName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getVersion() {
        return version;
    }
}
