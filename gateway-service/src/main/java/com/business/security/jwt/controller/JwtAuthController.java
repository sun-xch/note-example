package com.business.security.jwt.controller;

import com.business.common.rest.RestResult;
import com.business.security.jwt.service.JwtAuthService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class JwtAuthController {

    @Resource
    JwtAuthService jwtAuthService;

    /**
     * 登录认证换取JWT令牌
     * @param map
     * @return
     */
    @RequestMapping(value = "/login")
    public RestResult login(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return RestResult.error("用户或密码不能为空");
        }
        try{
            return RestResult.success(jwtAuthService.login(username,password));
        }catch (Exception e){
            return RestResult.error("用户或密码错误");
        }
    }

    @RequestMapping(value = "/resetToken")
    public RestResult refresh(@RequestHeader("${jwt.header}") String token){
        return RestResult.success(jwtAuthService.refreshToken(token));
    }
}
