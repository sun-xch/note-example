package com.business.sys.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuDao {


    public List getAuthMenu(List<String> roleIds);
}
