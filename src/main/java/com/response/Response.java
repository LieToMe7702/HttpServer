package com.response;

import com.resource.HttpStatus;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Response {
    private String content = "";
    private String funcName;
    private HttpStatus status;
    public void send(OutputStream outputStream) {
        try {
            var responseSb = new StringBuilder();
            var firstLine = "HTTP/1.0 " + status.getStatusCode()+ " "+ status.getDescription() + "\r\n";
            responseSb.append(firstLine);
            responseSb.append("\r\n");
            if(!content.isEmpty()){
                responseSb.append(content);
                responseSb.append("\r\n");
            }
            var responseStr1 = "HTTP/1.0 404 Not found\r\n\r\n";
            var responseStr = responseSb.toString();
            System.out.println(responseStr);
            System.out.println(responseStr1);
            var bytes = responseStr.getBytes(StandardCharsets.UTF_8);
            outputStream.write(bytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }


    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
