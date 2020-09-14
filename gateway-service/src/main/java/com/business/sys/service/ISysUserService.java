package com.business.sys.service;

import com.business.common.rest.RestTableResult;
import com.business.sys.dto.SysUserDto;
import com.business.sys.entity.SysUser;

import java.util.List;

public interface ISysUserService {

    public SysUser info(SysUser sysUser);

    public RestTableResult<SysUserDto> getUserList(SysUserDto sysUserDto);
}
