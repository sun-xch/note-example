package com.business.sys.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao {

    public List<String> getRoleIdByUserId(String uuid);
}
