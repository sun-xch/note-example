package com.business.sys.service;

import com.business.sys.dto.SysMenuDto;
import com.business.sys.entity.SysMenu;

import java.util.List;

public interface ISysMenuService {

    public List<SysMenuDto> getAuthMenu(String username);

    public List<SysMenu> getMenu(SysMenu sysMenu);

    public void saveMenu(SysMenu sysMenu);
}
