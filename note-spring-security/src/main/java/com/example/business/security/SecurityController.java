package com.example.business.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    /**
     * 登录之后的首页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("biz1")
    public String biz1(){
        return "biz1";
    }

    @GetMapping("biz2")
    public String biz2(){
        return "biz2";
    }

}
