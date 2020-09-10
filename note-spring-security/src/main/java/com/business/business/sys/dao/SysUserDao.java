package com.business.business.sys.dao;

import com.business.business.sys.entity.SysUser;
import com.business.config.security.MyUserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDao {

    public MyUserDetails selectUser(SysUser sysUser);

    public List<SysUser> selectAllUser(SysUser sysUser);

    public List<String> findUrlByUserName(String userName);
}
