package com.business.config.security.jwt;

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

    @RequestMapping(value = "/authentication")
    public RestResponseResult login(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return RestResponseResult.error("用户或密码不能为空");
        }
        try{
            return RestResponseResult.success(jwtAuthService.login(username,password));
        }catch (Exception e){
            return RestResponseResult.error("用户或密码错误");
        }
    }

    @RequestMapping(value = "/refreshToken")
    public RestResponseResult refresh(@RequestHeader("${jwt.header}") String token){
        return RestResponseResult.success(jwtAuthService.refreshToken(token));
    }
}
