package com.business.sys.controller;

import com.business.common.rest.RestResult;
import com.business.sys.entity.SysUser;
import com.business.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/info")
    public RestResult<SysUser> info(@RequestBody SysUser sysUser){
        SysUser info = sysUserService.info(sysUser);
        return RestResult.success(info);
    }

}
