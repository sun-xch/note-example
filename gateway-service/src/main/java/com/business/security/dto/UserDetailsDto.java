package com.business.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsDto implements UserDetails {

    private  String username;

    private String password;

    private boolean accountNotExpired;  //是否没过期

    private boolean accountNotLocked;   //是否没被锁

    private boolean credentialsNotExpired;  //是否没过期

    private boolean enabled;    //账号是否可用

    private Collection<? extends GrantedAuthority> authorities; //用户权限集合


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNotExpired(boolean accountNotExpired) {
        this.accountNotExpired = accountNotExpired;
    }

    public void setAccountNotLocked(boolean accountNotLocked) {
        this.accountNotLocked = accountNotLocked;
    }

    public void setCredentialsNotExpired(boolean credentialsNotExpired) {
        this.credentialsNotExpired = credentialsNotExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
