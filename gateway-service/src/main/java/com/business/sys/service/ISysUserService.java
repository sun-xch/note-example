package com.business.sys.service;

import com.business.common.rest.RestTableResult;
import com.business.sys.dto.SysUserDto;
import com.business.sys.entity.SysUser;


public interface ISysUserService {

    public SysUser info(SysUser sysUser);

    public RestTableResult<SysUserDto> getUserList(SysUserDto sysUserDto);

    public void addSingleUser(SysUser sysUser);
}
