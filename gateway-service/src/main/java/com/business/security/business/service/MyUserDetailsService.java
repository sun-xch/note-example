package com.business.security.business.service;

import com.business.security.business.dao.SysUserDao;
import com.business.security.business.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //加载用户信息
        UserDetailsDto userDetailsDto = sysUserDao.selectUserByUsername(username);

        userDetailsDto.setEnabled(true);

        return userDetailsDto;
    }

}
