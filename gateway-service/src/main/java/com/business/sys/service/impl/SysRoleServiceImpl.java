package com.business.sys.service.impl;

import com.business.common.utils.UUIDUtil;
import com.business.sys.dao.SysRoleDao;
import com.business.sys.dao.SysRoleMenuDao;
import com.business.sys.dto.SysRoleDto;
import com.business.sys.entity.SysRole;
import com.business.sys.entity.SysRoleMenu;
import com.business.sys.service.ISysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

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

    @Transactional
    @Override
    public void saveRole(SysRoleDto sysRoleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDto,sysRole);
        String roleUuid = sysRole.getUuid();
        if(StringUtils.isBlank(roleUuid)){
            // 新增数据
            roleUuid = UUIDUtil.get32UUID();
            sysRole.setUuid(roleUuid);
            sysRoleDao.insertRole(sysRole);
        }else{
            // 修改数据
            sysRoleDao.selectiveUpdateRole(sysRole);
        }
        // 修改 角色绑定的菜单信息 修改方式为 先删除再新增
        List<String> menuIds = sysRoleDto.getMenuIds();
        List<SysRoleMenu> list = new ArrayList<>();
        for (String menuId : menuIds){
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setUuid(UUIDUtil.get32UUID());
            sysRoleMenu.setRoleId(roleUuid);
            sysRoleMenu.setMenuId(menuId);
            list.add(sysRoleMenu);
        }
        // 删除此角色下所有数据
        sysRoleMenuDao.deleteByRoleId(roleUuid);
        // 批量新增此角色绑定的菜单信息
        if(list.size() > 0){
            sysRoleMenuDao.batchInsert(list);
        }
    }

    @Transactional
    @Override
    public void deleteRole(SysRole sysRole) {
        sysRoleDao.deleteById(sysRole.getUuid());
        sysRoleMenuDao.deleteByRoleId(sysRole.getUuid());
    }
}
