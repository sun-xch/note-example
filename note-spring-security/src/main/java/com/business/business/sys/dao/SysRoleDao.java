package com.business.business.sys.dao;

import com.business.business.sys.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao {

    public List<String> selectRoleList(SysUser sysUser);

}
