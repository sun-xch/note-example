package com.business.sys.service;

import com.business.sys.entity.SysRole;

import java.util.List;

public interface ISysRoleService {

    public List<String> getRoleIdByUserId(String uuid);

    public List<SysRole> getRoleList(SysRole sysRole);
}
