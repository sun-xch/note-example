package com.business.business.common.entity;

import java.time.LocalDateTime;

/**
 * 验证码实体
 */
public class CaptchaCode {

    private String code;

    private LocalDateTime expireTime;

    /**
     * 设置验证码 并设置过期时间
     * @param code
     * @param expireAfterSeconds
     */
    public CaptchaCode(String code, int expireAfterSeconds){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
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
}
