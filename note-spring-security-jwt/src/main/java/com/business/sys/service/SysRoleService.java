package com.business.sys.service;


import com.business.sys.entity.SysUser;

import java.util.List;

public interface SysRoleService {

    public List<String> selectRoleList(SysUser sysUser);
}
