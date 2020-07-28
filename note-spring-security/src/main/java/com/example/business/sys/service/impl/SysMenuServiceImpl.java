package com.example.business.sys.service.impl;

import com.example.business.sys.dao.SysMenuDao;
import com.example.business.sys.entity.SysMenu;
import com.example.business.sys.entity.SysRole;
import com.example.business.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public List<String> selectMenuList(List<String> list) {
        return sysMenuDao.selectMenuList(list);
    }
}
