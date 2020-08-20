package com.example.business.sys.service;

import com.example.business.sys.entity.SysUser;
import com.example.config.security.MyUserDetails;

import java.util.List;

public interface SysUserService {

    public MyUserDetails selectUser(SysUser sysUser);

    public List<SysUser> selectAllUser(SysUser sysUser);

    public List<String> findUrlByUserName(String userName);

}
