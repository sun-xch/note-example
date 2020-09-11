package com.business.security.handler;

import com.business.common.rest.RestResult;
import com.business.common.utils.JwtTokenUtil;
import com.business.security.business.service.MyUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${spring.security.loginType}")
    private String loginType;

    @Resource
    MyUserDetailsService myUserDetailsService;

    @Resource
    JwtTokenUtil jwtTokenUtil;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        if("JSON".equalsIgnoreCase(loginType)){
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(phone);
            String token = jwtTokenUtil.generateToken(userDetails);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(RestResult.success(token)));
        }else{
            //跳转到登录之前请求的页面
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
