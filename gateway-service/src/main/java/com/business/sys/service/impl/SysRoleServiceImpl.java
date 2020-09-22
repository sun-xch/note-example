package com.business.sys.service.impl;

import com.business.sys.dao.SysRoleDao;
import com.business.sys.entity.SysRole;
import com.business.sys.service.ISysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    /**
     * 根据用户主键 获取 用户所属的所有角色主键
     * @param uuid
     * @return
     */
    @Override
    public List<String> getRoleIdByUserId(String uuid) {
        return sysRoleDao.getRoleIdByUserId(uuid);
    }

    @Override
    public List<SysRole> getRoleList(SysRole sysRole) {
        return sysRoleDao.getRoleList(sysRole);
    }
}
