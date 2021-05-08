package com.example.common.rest;

public enum ResultCodeMsg {

    /* 成功状态码 */
    SUCCESS(200, "成功"),
    ERROR(500,"失败"),

    ;

    private Integer code;
    private String msg;


    ResultCodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    public static String getMessage(String name) {
        for (ResultCodeMsg item : ResultCodeMsg.values()) {
            if (item.name().equals(name)) {
                return item.msg;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCodeMsg item : ResultCodeMsg.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

}
