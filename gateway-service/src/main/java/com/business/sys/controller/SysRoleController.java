package com.business.sys.controller;

import com.business.common.rest.RestTableResult;
import com.business.common.rest.ResultCodeMsg;
import com.business.sys.entity.SysRole;
import com.business.sys.service.ISysRoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Resource
    private ISysRoleService sysRoleService;

    @RequestMapping("/getRoleList")
    public RestTableResult<SysRole> getRoleList(@RequestBody SysRole sysRole){
        List<SysRole> roleList = sysRoleService.getRoleList(sysRole);
        return new RestTableResult(ResultCodeMsg.SUCCESS.code(),ResultCodeMsg.SUCCESS.msg(),roleList,Long.valueOf(roleList.size()));
    }
}
