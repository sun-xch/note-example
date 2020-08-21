package com.example.config.security;

import com.example.config.filter.CaptchaCodeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启方法认证  在方法中加注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CaptchaCodeFilter captchaCodeFilter;

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Resource
    private DataSource dataSource;

    @Resource
    private SmsCodeSecurityConfig smsCodeSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(captchaCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and().rememberMe()//密码记住我配置
                .tokenValiditySeconds(2*24*60*60)//记住密码令牌有效期 2天
                .tokenRepository(persistentTokenRepository())//记住密码持久化token
                .and().csrf().disable().formLogin()
                .loginPage("/login.html")//指定登录页面
                .loginProcessingUrl("/login")//登录认证处理
                //.defaultSuccessUrl("/index")//登录成功后跳转到哪个页面
                .successHandler(myAuthenticationSuccessHandler)//登录请求成功后的自定义处理逻辑
                .failureHandler(myAuthenticationFailureHandler)//登录请求失败后的自定义处理逻辑
                .and().apply(smsCodeSecurityConfig).and()//使smsCodeSecurityConfig配置生效
                .authorizeRequests() //下面的都是授权的配置
                .antMatchers("/login.html","/login","/captcha","/getSmsCode","/smslogin").permitAll()//不要登录认证的请求
                .antMatchers("/index").authenticated()//登录就可以访问 不需要权限认证
                //.antMatchers("/system/*").access("hasRole('admin') or hasAnyAuthority('ROLE_admin')")
                .anyRequest().access("@rbacService.hasPermssion(request,authentication)")//资源校验
//                .antMatchers("/biz1","/biz2")//需要对外暴露的资源路径
//                .hasAnyAuthority("ROLE_user","ROLE_admin")//user角色 和 admin角色都可以访问
//                .antMatchers("/sysLog").hasAnyAuthority("/sysLog")
//                .antMatchers("/sysUser").hasAnyAuthority("/sysUser")
//                .anyRequest() //任何请求
//                .authenticated() //访问任何资源都需要身份认证
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //获取用户所有的角色和资源信息
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
