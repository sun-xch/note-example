package com.example.business.controller;

import com.example.business.entity.UserInfo;
import com.example.business.service.IUserInfoService;
import com.example.common.rest.RestResult;
import com.example.common.rest.RestTableResult;
import com.example.common.rest.ResultCodeMsg;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @PostMapping("/getAllUserInfo")
    public RestResult<List<UserInfo>> getAllUserInfo(@RequestBody UserInfo userInfo){
        List<UserInfo> list = userInfoService.getAllUserInfo(userInfo);
        return RestResult.success(list);
    }

    @PostMapping("/getTableByCondition")
    public RestTableResult<UserInfo> getTableByCondition(@RequestBody UserInfo userInfo){
        PageInfo pageInfo = userInfoService.getTableByCondition(userInfo);
        return new RestTableResult<UserInfo>(ResultCodeMsg.SUCCESS.code(), ResultCodeMsg.SUCCESS.msg(), pageInfo.getList(), pageInfo.getTotal());
    }

}
