package com.business.sys.controller;

import com.business.common.rest.RestTableResult;
import com.business.common.rest.ResultCodeMsg;
import com.business.common.utils.JwtTokenUtil;
import com.business.sys.dto.SysMenuDto;
import com.business.sys.entity.SysMenu;
import com.business.sys.service.ISysMenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private ISysMenuService sysMenuService;

    @RequestMapping("/getAuthMenu")
    public RestTableResult<SysMenuDto> getAuthMenu(HttpServletRequest request, HttpServletResponse response){
        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        List<SysMenuDto> authMenu = sysMenuService.getAuthMenu(username);
        return new RestTableResult<SysMenuDto>(ResultCodeMsg.SUCCESS.code(),ResultCodeMsg.SUCCESS.msg(),authMenu,Long.valueOf(authMenu.size()));
    }

    @RequestMapping("/getMenu")
    public RestTableResult<SysMenu> getMenu(@RequestBody SysMenu sysMenu){
        List<SysMenu> menu = sysMenuService.getMenu(sysMenu);
        return new RestTableResult<SysMenu>(ResultCodeMsg.SUCCESS.code(),ResultCodeMsg.SUCCESS.msg(),menu,Long.valueOf(menu.size()));
    }


}
