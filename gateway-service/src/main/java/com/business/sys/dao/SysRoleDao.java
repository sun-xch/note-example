package com.business.sys.dao;

import com.business.sys.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao {

    public List<String> getRoleIdByUserId(String uuid);

    public List<SysRole> getRoleList(SysRole sysRole);
}
