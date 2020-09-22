package com.business.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable {

    private static final long serialVersionUID = -8412454111818391326L;

    private String uuid;

    private String roleCode;

    private String roleName;

    private String roleDesc;

    private String sort;

    private Date createTime;

    private String createUserUuid;

    private Date updateTime;

    private String updateUserUuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserUuid() {
        return createUserUuid;
    }

    public void setCreateUserUuid(String createUserUuid) {
        this.createUserUuid = createUserUuid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserUuid() {
        return updateUserUuid;
    }

    public void setUpdateUserUuid(String updateUserUuid) {
        this.updateUserUuid = updateUserUuid;
    }
}
