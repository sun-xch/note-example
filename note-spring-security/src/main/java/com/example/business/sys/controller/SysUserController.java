package com.example.business.sys.controller;

import com.example.business.sys.entity.SysUser;
import com.example.business.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/userInfo")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/selectAllUser")
    public List<SysUser> selectAllUser(SysUser sysUser){
        return sysUserService.selectAllUser(sysUser);
    }

}
