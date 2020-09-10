package com.business.sys.dao;

import com.business.sys.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao {

    public List<String> selectRoleList(SysUser sysUser);

}
