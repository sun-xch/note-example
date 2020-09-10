package com.business.sys.dao;

import com.business.config.security.MyUserDetails;
import com.business.sys.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDao {

    public MyUserDetails selectUser(SysUser sysUser);

    public List<SysUser> selectAllUser(SysUser sysUser);

    public List<String> findUrlByUserName(String userName);
}
