package com.business.sys.service.impl;

import com.business.sys.dao.SysRoleDao;
import com.business.sys.entity.SysUser;
import com.business.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public List<String> selectRoleList(SysUser sysUser) {
        return sysRoleDao.selectRoleList(sysUser);
    }
}
