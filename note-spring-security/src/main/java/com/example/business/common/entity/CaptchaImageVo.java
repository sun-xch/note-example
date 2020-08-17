package com.example.business.common.entity;

import java.time.LocalDateTime;

/**
 * 验证码实体
 */
public class CaptchaImageVo {

    private String code;

    private LocalDateTime expireTime;

    /**
     * 设置验证码 并设置过期时间
     * @param code
     * @param expireAfterSeconds
     */
    public CaptchaImageVo(String code, int expireAfterSeconds){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
