package com.business.sys.controller;

import com.business.common.rest.RestResult;
import com.business.common.rest.RestTableResult;
import com.business.common.rest.ResultCodeMsg;
import com.business.sys.dto.SysRoleDto;
import com.business.sys.entity.SysRole;
import com.business.sys.service.ISysRoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 角色管理：查询角色信息使用
     * @param sysRole
     * @return
     */
    @RequestMapping("/getRoleList")
    public RestTableResult<SysRole> getRoleList(@RequestBody SysRole sysRole){
        List<SysRole> roleList = sysRoleService.getRoleList(sysRole);
        return new RestTableResult(ResultCodeMsg.SUCCESS.code(),ResultCodeMsg.SUCCESS.msg(),roleList,Long.valueOf(roleList.size()));
    }

    /**
     * 角色管理：新增 修改角色信息 使用
     * @param sysRoleDto
     * @return
     */
    @PostMapping("/saveRole")
    public RestResult saveRole(@RequestBody SysRoleDto sysRoleDto){
        sysRoleDto.setCreateTime(LocalDateTime.now());
        sysRoleService.saveRole(sysRoleDto);
        return RestResult.success("");
    }

    @PostMapping("/deleteRole")
    public RestResult deleteRole(@RequestBody SysRole sysRole){
        sysRoleService.deleteRole(sysRole);
        return RestResult.success("");
    }
}
