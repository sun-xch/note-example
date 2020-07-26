package com.example.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin()
                .loginPage("/login.html")//指定登录页面
                .loginProcessingUrl("/login")//登录认证处理
                //.defaultSuccessUrl("/index")//登录成功后跳转到哪个页面
                .successHandler(myAuthenticationSuccessHandler)//登录请求成功后的自定义处理逻辑
                .failureHandler(myAuthenticationFailureHandler)//登录请求失败后的自定义处理逻辑
                .and()
                .authorizeRequests() //下面的都是授权的配置
                .antMatchers("/login.html","/login").permitAll()//不要登录认证的请求
                .anyRequest() //任何请求
                .authenticated() //访问任何资源都需要身份认证
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)//session创建策略
                .invalidSessionUrl("/login.html")//session超时跳转到login.html
                .maximumSessions(1)//最大session数 只能有一个用户在登录
                .maxSessionsPreventsLogin(false)//true登录之后不能再登录，false后面登录会把之前登录下线
                .expiredSessionStrategy(new MyExpiredSessionStrategy());//session超时策略
                //.and()
                //.csrf().disable();//跨站请求伪造防护 关闭
    }

}
