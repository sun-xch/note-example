package com.business.config.security.jwt;

public class RestResponseResult {

    String message;

    int code;

    Object data;

    boolean success;

    public static RestResponseResult success(Object data){
        RestResponseResult restResponseResult = new RestResponseResult();
        restResponseResult.setSuccess(true);
        restResponseResult.setCode(200);
        restResponseResult.setMessage("success");
        restResponseResult.setData(data);
        return restResponseResult;
    }

    public static RestResponseResult error(){
        RestResponseResult restResponseResult = new RestResponseResult();
        restResponseResult.setSuccess(false);
        restResponseResult.setCode(400);
        restResponseResult.setMessage("fail");
        return restResponseResult;
    }

    public static RestResponseResult error(String message){
        RestResponseResult restResponseResult = new RestResponseResult();
        restResponseResult.setSuccess(false);
        restResponseResult.setCode(400);
        restResponseResult.setMessage(message);
        return restResponseResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
