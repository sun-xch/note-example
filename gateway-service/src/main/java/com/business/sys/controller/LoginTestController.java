package com.business.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loginTest")
public class LoginTestController {

    @RequestMapping("/biz1")
    public String biz1(){
        return "biz1";
    }
}
