package com.example.config.security;

import com.example.business.sys.dao.SysMenuDao;
import com.example.business.sys.dao.SysRoleDao;
import com.example.business.sys.dao.SysUserDao;
import com.example.business.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.加载基础用户信息
        SysUser model = new SysUser();
        model.setUserName(username);
        MyUserDetails myUserDetails = sysUserDao.selectUser(model);
        //2.加载用户角色列表
        List<String> sysRoles = sysRoleDao.selectRoleList(model);
        //3.通过用户角色列表加载用户的资源权限列表
        List<String> sysMenus = sysMenuDao.selectMenuList(sysRoles);
        //角色是一个特殊的权限，ROLE_前缀
        List<String> sysRoleList = sysRoles.stream().map(item -> "ROLE_" + item).collect(Collectors.toList());
        sysMenus.addAll(sysRoleList);

        myUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",sysMenus)));

        myUserDetails.setEnabled(true);
        return myUserDetails;
    }

}
