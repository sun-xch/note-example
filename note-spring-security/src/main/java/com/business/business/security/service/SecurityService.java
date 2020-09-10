package com.business.business.security.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    /**
     * 当前登录用户有 admin角色才可以访问 否则抛出异常
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    public String test1(){
        return "";
    }

    @PostAuthorize("returnObject.name == authentication.name")
    public String test2(){
        return "";
    }

    @PreFilter(filterTarget = "ids",value = "filterObject%2 == 0")
    public String test3(){
        return "";
    }

    @PostFilter("filterObject.name == authentication.name")
    public String test4(){
        return "";
    }
}
