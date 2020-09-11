package com.business.sys.service.impl;

import com.business.sys.dao.SysUserDao;
import com.business.sys.entity.SysUser;
import com.business.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser info(SysUser sysUser) {
        return sysUserDao.info(sysUser);
    }

}
