package com.example.business.common.entity;

import java.time.LocalDateTime;

public class SmsCode {

    private String code;

    private LocalDateTime expireTime;

    private String phone;

    /**
     * 设置验证码 并设置过期时间
     * @param code
     * @param expireAfterSeconds
     */
    public SmsCode(String code, int expireAfterSeconds, String phone){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
        this.phone = phone;
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
