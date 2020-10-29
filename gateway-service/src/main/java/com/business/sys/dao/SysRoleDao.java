package com.business.sys.dao;

import com.business.sys.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao {

    public List<String> getRoleIdByUserId(String uuid);

    public List<SysRole> getRoleList(SysRole sysRole);

    public int insertRole(SysRole sysRole);

    public int selectiveUpdateRole(SysRole sysRole);

    public int deleteById(String uuid);
}
