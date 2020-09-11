package com.business.security.business.dao;

import com.business.security.business.dto.UserDetailsDto;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsDao {

    public UserDetailsDto selectUserByUsername(String userName);
}
