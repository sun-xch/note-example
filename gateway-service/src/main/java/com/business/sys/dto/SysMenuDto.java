package com.business.sys.dto;

import com.business.sys.entity.SysMenu;

import java.util.List;

public class SysMenuDto extends SysMenu {

    private List<SysMenuDto> childMenu;

    public List<SysMenuDto> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<SysMenuDto> childMenu) {
        this.childMenu = childMenu;
    }
}
