package com.example.business.service;

import com.example.business.entity.UserInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserInfoService {

    List<UserInfo> getAllUserInfo(UserInfo userInfo);

    PageInfo getTableByCondition(UserInfo userInfo);

    void batchInsert(List<UserInfo> list);
}
