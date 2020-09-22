package com.business.sys.dto;

import com.business.sys.entity.SysMenu;

import java.util.List;

public class SysMenuDto extends SysMenu {

    private String roleId;

    private String roleMenuId;

    private List<SysMenuDto> childMenu;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(String roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public List<SysMenuDto> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<SysMenuDto> childMenu) {
        this.childMenu = childMenu;
    }
}
