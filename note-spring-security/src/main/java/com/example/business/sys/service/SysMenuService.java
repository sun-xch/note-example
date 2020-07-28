package com.example.business.sys.service;

import com.example.business.sys.entity.SysMenu;
import com.example.business.sys.entity.SysRole;

import java.util.List;

public interface SysMenuService {

    public List<String> selectMenuList(List<String> list);
}
