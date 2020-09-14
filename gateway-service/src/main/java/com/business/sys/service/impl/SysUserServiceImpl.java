package com.business.sys.service.impl;

import com.business.common.rest.RestTableResult;
import com.business.common.rest.ResultCodeMsg;
import com.business.sys.dao.SysUserDao;
import com.business.sys.dto.SysUserDto;
import com.business.sys.entity.SysUser;
import com.business.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser info(SysUser sysUser) {
        return sysUserDao.info(sysUser);
    }

    @Override
    public RestTableResult<SysUserDto> getUserList(SysUserDto sysUserDto) {
        List<SysUserDto> userList = sysUserDao.getUserList(sysUserDto);
        return new RestTableResult(ResultCodeMsg.SUCCESS.code(),ResultCodeMsg.SUCCESS.msg(),userList,Long.valueOf(userList.size()));
    }

}
