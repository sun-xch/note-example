package com.business.sys.dao;

import com.business.sys.dto.SysUserDto;
import com.business.sys.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDao {

    public SysUser info(SysUser sysUser);

    public List<SysUserDto> getUserList(SysUserDto sysUserDto);

    public int addSingleUser(SysUser sysUser);
}
