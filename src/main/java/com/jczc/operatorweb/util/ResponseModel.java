package com.jczc.operatorweb.util;

public class ResponseModel<T> {

    private boolean status;
    private String msg;
    private T data;

    public ResponseModel(){

    }

    public ResponseModel(boolean status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
