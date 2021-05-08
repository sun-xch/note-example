package com.example.common.rest;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class RestTableResult<T> implements Serializable {

    private Integer code;

    private String msg;

    private Map<String,Object> data = new HashMap();

    public RestTableResult() {
    }

    public RestTableResult(Integer code, String msg, List<T> list, Long total) {
        this.code = code;
        this.msg = msg;
        data.put("items",list);
        data.put("total",total);
    }
}
