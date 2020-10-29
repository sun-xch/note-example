package com.business.sys.service;

import com.business.sys.dto.SysMenuDto;
import com.business.sys.entity.SysMenu;

import java.util.List;

public interface ISysMenuService {

    public List<SysMenuDto> getAuthMenu(String username, String parentId, String type);

    public List<SysMenuDto> getMenuAndBindRole(SysMenuDto sysMenuDto, String parentId);

    public void saveMenu(SysMenu sysMenu);
}
