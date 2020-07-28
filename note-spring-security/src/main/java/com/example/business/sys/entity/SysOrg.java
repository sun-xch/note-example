package com.example.business.sys.entity;

import java.io.Serializable;

public class SysOrg implements Serializable {

    private static final long serialVersionUID = -4705522291093597300L;

    private String uuid;

    private String orgPid;

    private String orgPids;

    private String isLeaf;

    private String orgName;

    private String level;

    private String sort;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOrgPid() {
        return orgPid;
    }

    public void setOrgPid(String orgPid) {
        this.orgPid = orgPid;
    }

    public String getOrgPids() {
        return orgPids;
    }

    public void setOrgPids(String orgPids) {
        this.orgPids = orgPids;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
