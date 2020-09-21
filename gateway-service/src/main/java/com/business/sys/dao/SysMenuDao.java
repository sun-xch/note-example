package com.business.sys.dao;

import com.business.sys.entity.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuDao {


    public List getAuthMenu(List<String> roleIds);

    public List<SysMenu> getMenu(SysMenu sysMenu);

    public int insertMenu(SysMenu sysMenu);

    public int updateMenu(SysMenu sysMenu);
}
