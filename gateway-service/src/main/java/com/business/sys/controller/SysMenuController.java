package com.business.sys.controller;

import com.business.common.rest.RestResult;
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

    /**
     * 查询 用户所属权限下所有以0为父节点的所有目录及菜单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getAuthMenu")
    public RestTableResult<SysMenuDto> getAuthMenu(HttpServletRequest request, HttpServletResponse response){
        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        List<SysMenuDto> authMenu = sysMenuService.getAuthMenu(username,"0","2");
        return new RestTableResult<SysMenuDto>(ResultCodeMsg.SUCCESS.code(),ResultCodeMsg.SUCCESS.msg(),authMenu,Long.valueOf(authMenu.size()));
    }

    /**
     * 查询 用户所属权限下所有以-1为父节点的所有目录及菜单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getAuthAllMenu")
    public RestTableResult<SysMenuDto> getAuthAllMenu(HttpServletRequest request, HttpServletResponse response){
        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        List<SysMenuDto> authMenu = sysMenuService.getAuthMenu(username,"-1","2");
        return new RestTableResult<SysMenuDto>(ResultCodeMsg.SUCCESS.code(),ResultCodeMsg.SUCCESS.msg(),authMenu,Long.valueOf(authMenu.size()));
    }

    @RequestMapping("/getAllMenu")
    public RestTableResult<SysMenu> getAllMenu(@RequestBody SysMenu sysMenu){
        List<SysMenu> menu = sysMenuService.getAllMenu(sysMenu);
        return new RestTableResult<SysMenu>(ResultCodeMsg.SUCCESS.code(),ResultCodeMsg.SUCCESS.msg(),menu,Long.valueOf(menu.size()));
    }

    @RequestMapping("/saveMenu")
    public RestResult<String> saveMenu(@RequestBody SysMenu sysMenu){
        sysMenuService.saveMenu(sysMenu);
        return RestResult.success("");
    }

    @RequestMapping("/getMenuAndBindRole")
    public RestTableResult<SysMenuDto> getMenuAndBindRole(@RequestBody SysMenuDto sysMenuDto){
        List<SysMenuDto> menuAndBindRole = sysMenuService.getMenuAndBindRole(sysMenuDto);
        return new RestTableResult<SysMenuDto>(ResultCodeMsg.SUCCESS.code(),ResultCodeMsg.SUCCESS.msg(),menuAndBindRole,Long.valueOf(menuAndBindRole.size()));
    }


}
