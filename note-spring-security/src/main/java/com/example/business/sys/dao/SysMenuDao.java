package com.example.business.sys.dao;

import com.example.business.sys.entity.SysMenu;
import com.example.business.sys.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuDao {

    public List<String> selectMenuList(List<String> list);
}
