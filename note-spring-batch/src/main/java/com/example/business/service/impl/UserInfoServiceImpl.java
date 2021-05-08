package com.example.business.service.impl;

import com.example.business.entity.UserInfo;
import com.example.business.mapper.UserInfoMapper;
import com.example.business.service.IUserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getAllUserInfo(UserInfo userInfo) {
        return userInfoMapper.getAllUserInfo(userInfo);
    }

    @Override
    public PageInfo getTableByCondition(UserInfo userInfo) {
        PageHelper.startPage(userInfo.getPageNo(),userInfo.getPageSize()).setOrderBy("phone");
        List<UserInfo> list = userInfoMapper.getAllUserInfo(userInfo);
        return new PageInfo<>(list);
    }

}
