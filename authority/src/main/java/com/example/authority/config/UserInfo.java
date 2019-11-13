package com.example.authority.config;

import com.example.authority.dto.*;
import com.google.common.collect.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import java.util.*;

/**
 * @author ZhouFan
 * @date 2019/10/10 18:01
 * @description
 */
public class UserInfo implements UserDetails {
    private Integer id;

    private Integer userAge;

    private String userName;

    private String userPass;

    private Collection<? extends GrantedAuthority> authorities;


    public UserInfo(UserResponse response) {
        this.id =response.getId();
        this.userAge = response.getUserAge();
        this.userName = response.getUserName();
        this.userPass = response.getUserPass();
        this.authorities = Lists.newArrayList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPass;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return true;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getUserName() {
        return userName;
    }
}
