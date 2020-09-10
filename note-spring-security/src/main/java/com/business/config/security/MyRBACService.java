package com.business.config.security;

import com.business.business.sys.service.SysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("rbacService")
public class MyRBACService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Resource
    private SysUserService sysUserService;

    //判断某用户是否具有该请求资源的访问权限
    public boolean hasPermssion(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
            List<String> urls = sysUserService.findUrlByUserName(username);

            return urls.stream().anyMatch(
                url -> antPathMatcher.match(url,request.getRequestURI())
            );
        }
        return false;
    }
}
