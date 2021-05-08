package com.example.business.mapper;

import com.example.business.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    List<UserInfo> getAllUserInfo(UserInfo userInfo);

}
