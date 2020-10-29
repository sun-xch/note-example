package com.business.sys.dao;

import com.business.sys.entity.SysRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMenuDao {

    public int deleteByRoleId(String roleId);

    public int batchInsert(List<SysRoleMenu> list);
}
