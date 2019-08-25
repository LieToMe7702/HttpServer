package com.session;

import com.httpFunc.HttpFuncFactory;
import com.parser.HttpRequestParser;
import com.parser.IParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class HttpSession implements IHttpSession{

    private InputStream inputStream;
    private OutputStream outputStream;
    private String inputStr;
    private String funcName;
    private String url;
    private String version;
    private IParser httpRequestParser;
    private Socket socket;
    public HttpSession(InputStream inputStream, OutputStream outputStream, Socket socket) {
        this.inputStream = new BufferedInputStream(inputStream);
        this.outputStream = outputStream;
        this.socket = socket;
        httpRequestParser = new HttpRequestParser(this);
    }

    public void exec() throws IOException {

        try{
            var len = inputStream.available();
            if(len == 0) {
                close();
                return;
            }
            var buffer = new BufferedInputStream(inputStream).readNBytes(len);
            inputStr = new String(buffer);
            httpRequestParser.parse(inputStr);
            var response = HttpFuncFactory.GetInstane().Handle(this);
            if(response != null){
                response.send(outputStream);
            }
        }catch (IOException e){
            close();
        }

    }

    private void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("HttpSession close");
        //throw new SocketException("HttpSession close");
    }

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
