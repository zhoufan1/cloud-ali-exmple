package com.knife.authority.provider.config;

import com.knife.user.dto.response.UserResponse;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserInfo implements UserDetails {
    private Integer id;

    private Integer userAge;

    private String userName;

    private String userPass;

    private Collection<? extends GrantedAuthority> authorities;


    public UserInfo(UserResponse response) {
        this.id = response.getId();
        this.userAge = response.getUserAge();
        this.userName = response.getUserName();
        this.userPass = response.getPassword();
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

}