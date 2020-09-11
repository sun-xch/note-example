package com.business.security.filter;

import com.business.security.handler.MyAuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SmsCodeFilter extends OncePerRequestFilter {

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //过滤登录请求
        if("/smslogin".equals(request.getRequestURI()) && "post".equalsIgnoreCase(request.getMethod())){
            //验证 验证码是否匹配
            try{
                validate(new ServletWebRequest(request));
            }catch (AuthenticationException e){
                myAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) {
        String phone = request.getParameter("phone");
        String smsCode = request.getParameter("smsCode");

        if(StringUtils.isEmpty(phone)){
            throw new SessionAuthenticationException("手机号不能为空");
        }

        if(StringUtils.isEmpty(smsCode)){
            throw new SessionAuthenticationException("验证码不能为空");
        }

        String codeInRedis = "123456";
        if(StringUtils.isEmpty(codeInRedis)){
            throw new SessionAuthenticationException("验证码不存在");
        }

        if(!smsCode.equals(codeInRedis)){
            throw new SessionAuthenticationException("验证码不匹配");
        }


    }

}
