package com.business.sys.service.impl;

import com.business.config.security.MyUserDetails;
import com.business.sys.dao.SysUserDao;
import com.business.sys.entity.SysUser;
import com.business.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;


    @Override
    public MyUserDetails selectUser(SysUser sysUser) {
        return sysUserDao.selectUser(sysUser);
    }

    @Override
    public List<SysUser> selectAllUser(SysUser sysUser) {
        return sysUserDao.selectAllUser(sysUser);
    }

    @Override
    public List<String> findUrlByUserName(String userName) {
        return sysUserDao.findUrlByUserName(userName);
    }
}
