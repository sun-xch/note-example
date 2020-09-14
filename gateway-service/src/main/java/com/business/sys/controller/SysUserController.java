package com.business.sys.controller;

import com.business.common.rest.RestResult;
import com.business.common.rest.RestTableResult;
import com.business.common.utils.JwtTokenUtil;
import com.business.sys.dto.SysUserDto;
import com.business.sys.entity.SysUser;
import com.business.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/info")
    public RestResult<SysUser> info(HttpServletRequest request, HttpServletResponse response){
        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        SysUser info = sysUserService.info(sysUser);
        return RestResult.success(info);
    }

    @RequestMapping("/getUserList")
    public RestTableResult<SysUserDto> getUserList(@RequestBody SysUserDto sysUserDto){
        return sysUserService.getUserList(sysUserDto);
    }

}
