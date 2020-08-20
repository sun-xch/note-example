package com.example.config.filter;

import com.example.business.common.contants.MyContants;
import com.example.business.common.entity.CaptchaCode;
import com.example.business.common.entity.SmsCode;
import com.example.business.sys.service.SysUserService;
import com.example.config.security.MyAuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
public class SmsCodeFilter extends OncePerRequestFilter {

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private SysUserService sysUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //过滤登录请求
        if(StringUtils.equals("/smslogin",request.getRequestURI()) && StringUtils.equalsIgnoreCase("post",request.getMethod())){
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
        HttpSession session = request.getRequest().getSession();
        String phone = request.getParameter("phone");
        String smsCode = request.getParameter("smsCode");

        if(StringUtils.isEmpty(phone)){
            throw new SessionAuthenticationException("手机号不能为空");
        }

        if(StringUtils.isEmpty(smsCode)){
            throw new SessionAuthenticationException("验证码不能为空");
        }

        SmsCode codeInSession = (SmsCode) session.getAttribute(MyContants.SMS_SESSION_KEY);
        if(Objects.isNull(codeInSession)){
            throw new SessionAuthenticationException("验证码不存在");
        }

        if(codeInSession.isExpired()){
            session.removeAttribute(MyContants.SMS_SESSION_KEY);
            throw new SessionAuthenticationException("验证码已过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(),smsCode)){
            throw new SessionAuthenticationException("验证码不匹配");
        }


    }

}
