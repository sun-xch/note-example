package com.example.business.sys.service;

import com.example.business.sys.entity.SysRole;
import com.example.business.sys.entity.SysUser;

import java.util.List;

public interface SysRoleService {

    public List<String> selectRoleList(SysUser sysUser);
}
