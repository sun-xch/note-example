package com.business.sys.dto;

import com.business.sys.entity.SysRole;

import java.util.List;

public class SysRoleDto extends SysRole {

    private List<String> menuIds;

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }
}
