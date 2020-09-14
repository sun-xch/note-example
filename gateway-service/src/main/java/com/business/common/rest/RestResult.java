package com.business.common.rest;

import lombok.Data;

import java.io.Serializable;

@Data
public class RestResult<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public RestResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RestResult<T> success(T data){
        return new RestResult<T>(ResultCodeMsg.SUCCESS.code(),ResultCodeMsg.SUCCESS.msg(),data);
    }

    public static <T> RestResult<T> success(ResultCodeMsg resultCodeMsg,T data){
        return new RestResult<T>(resultCodeMsg.code(),resultCodeMsg.msg(),data);
    }

    public static <T> RestResult<T> error(T data){
        return new RestResult<T>(ResultCodeMsg.ERROR.code(),ResultCodeMsg.ERROR.msg(),data);
    }

    public static <T> RestResult<T> error(ResultCodeMsg resultCodeMsg, T data){
        return new RestResult<T>(resultCodeMsg.code(),resultCodeMsg.msg(),data);
    }

}
