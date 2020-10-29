package com.business.sys.entity;

import java.io.Serializable;

public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = -5616813069303343321L;

    private String uuid;

    private String roleId;

    private String menuId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
