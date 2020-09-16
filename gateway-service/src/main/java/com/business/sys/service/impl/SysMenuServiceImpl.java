package com.business.sys.service.impl;

import com.business.sys.dao.SysMenuDao;
import com.business.sys.dto.SysMenuDto;
import com.business.sys.entity.SysMenu;
import com.business.sys.entity.SysUser;
import com.business.sys.service.ISysMenuService;
import com.business.sys.service.ISysRoleService;
import com.business.sys.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Resource
    private ISysUserService sysUserService;

    @Resource
    private ISysRoleService sysRoleService;

    @Resource
    private SysMenuDao sysMenuDao;

    @Override
    public List<SysMenuDto> getAuthMenu(String username) {
        //1.根据username获取用户主键
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        SysUser info = sysUserService.info(sysUser);
        //2.根据用户主键 获取用户所有的角色主键
        List<String> roleIds = sysRoleService.getRoleIdByUserId(info.getUuid());
        //3.根据角色主键 获取 所属的菜单信息
        List<SysMenu> authMenu = sysMenuDao.getAuthMenu(roleIds);
        List<SysMenuDto> sysMenuDtoList = menuListToTree(authMenu, "0");
        return sysMenuDtoList;
    }

    //递归List返回树状结构
    public List<SysMenuDto> menuListToTree(List<SysMenu> menuList, String parentId){
        List<SysMenuDto> resultList = new ArrayList<>();
        for(SysMenu sysMenu : menuList){
            SysMenuDto sysMenuDto = new SysMenuDto();
            BeanUtils.copyProperties(sysMenu,sysMenuDto);
            if(parentId.equals(sysMenu.getMenuPid())){
                List<SysMenuDto> sysMenuDtoList = menuListToTree(menuList, sysMenu.getUuid());
                sysMenuDto.setChildMenu(sysMenuDtoList);
                resultList.add(sysMenuDto);
            }
        }
        return resultList;
    }
}
