package com.business.sys.service;

import com.business.config.security.MyUserDetails;
import com.business.sys.entity.SysUser;

import java.util.List;

public interface SysUserService {

    public MyUserDetails selectUser(SysUser sysUser);

    public List<SysUser> selectAllUser(SysUser sysUser);

    public List<String> findUrlByUserName(String userName);

}
