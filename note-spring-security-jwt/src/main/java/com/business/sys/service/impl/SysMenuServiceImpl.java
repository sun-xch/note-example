package com.business.sys.service.impl;

import com.business.sys.dao.SysMenuDao;
import com.business.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public List<String> selectMenuList(List<String> list) {
        return sysMenuDao.selectMenuList(list);
    }
}
