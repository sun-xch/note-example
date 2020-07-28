package com.example.business.sys.dao;

import com.example.business.sys.entity.SysUser;
import com.example.config.security.MyUserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDao {

    public MyUserDetails selectUser(SysUser sysUser);

    public List<SysUser> selectAllUser(SysUser sysUser);
}
