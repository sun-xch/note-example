package com.example.business.common.controller;

import com.example.business.RestResponseResult;
import com.example.business.common.contants.MyContants;
import com.example.business.common.entity.SmsCode;
import com.example.business.sys.entity.SysUser;
import com.example.business.sys.service.SysUserService;
import com.example.config.security.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class SmsController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/getSmsCode", method = RequestMethod.GET)
    public RestResponseResult getSmsCode(@RequestParam String phone, HttpSession session){

        SysUser sysUser = new SysUser();
        sysUser.setUserName(phone);
        MyUserDetails myUserDetails = sysUserService.selectUser(sysUser);
        if(myUserDetails == null){
            return RestResponseResult.error("你输入的手机号未注册");
        }

        SmsCode smsCode = new SmsCode(RandomStringUtils.randomNumeric(4), 90, phone);

        //TODO 调用短信服务商接口发送短信

        log.info(smsCode.getCode() + "----->" + phone);

        session.setAttribute(MyContants.SMS_SESSION_KEY,smsCode);

        return RestResponseResult.success("");
    }
}
