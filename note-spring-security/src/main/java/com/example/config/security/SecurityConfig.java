package com.example.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")//指定登录页面
                .loginProcessingUrl("/login")//登录认证处理
                .defaultSuccessUrl("/index")//登录成功后跳转到哪个页面
                .and()
                .authorizeRequests() //下面的都是授权的配置
                .antMatchers("/login.html","/login").permitAll()//不要登录认证的请求
                .anyRequest() //任何请求
                .authenticated() //访问任何资源都需要身份认证
                .and()
                .csrf().disable();//跨站请求伪造防护 关闭
    }

}
