package com.example.business.sys.service.impl;

import com.example.business.sys.dao.SysRoleDao;
import com.example.business.sys.entity.SysRole;
import com.example.business.sys.entity.SysUser;
import com.example.business.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public List<String> selectRoleList(SysUser sysUser) {
        return sysRoleDao.selectRoleList(sysUser);
    }
}
