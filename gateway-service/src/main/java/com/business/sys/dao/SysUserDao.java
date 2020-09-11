package com.business.sys.dao;

import com.business.sys.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao {

    public SysUser info(SysUser sysUser);
}
