package com.business.security.dao;

import com.business.security.dto.UserDetailsDto;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao {

    public UserDetailsDto selectUserByUsername(String userName);
}
