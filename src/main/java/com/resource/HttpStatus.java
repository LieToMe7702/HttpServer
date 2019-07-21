package com.resource;

public enum HttpStatus {

    OK(200,"ok"),
    NotFound(404,"Not_found");

    private String description;
    private int statusCode;
    HttpStatus(int statusCode,String description){
        this.description = description;
        this.statusCode = statusCode;
    }

     public static HttpStatus GetStatusByStatus(int statusCode){
        for (var status : HttpStatus.values()) {
            if (status.getStatusCode() == statusCode) {
                return status;
            }
        }
        return null;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }
}
