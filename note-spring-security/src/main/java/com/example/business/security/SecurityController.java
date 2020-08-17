package com.example.business.security;

import com.example.business.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    /**
     * 登录之后的首页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/biz1")
    public String biz1(){
        securityService.test1();
        return "biz1";
    }

    @GetMapping("/biz2")
    public String biz2(){
        return "biz2";
    }

    @GetMapping("/sysLog")
    public String sysLog(){
        return "sysLog";
    }

    @GetMapping("/sysUser")
    public String sysUser(){
        return "sysUser";
    }

}
