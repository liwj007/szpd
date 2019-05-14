package com.liwj.szpd.utils;

public class ResponseData {
    private int code;
    private boolean status;
    private String message;
    private Object data;

    public ResponseData setSuccessData(Object data){
        this.code=200;
        this.status=true;
        this.data=data;
        return this;
    }

    public ResponseData setFail(int code, String message){
        this.code = code;
        this.status=false;
        this.message=message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
