package com.knife.authority.provider.config;

import com.knife.user.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class KnifeUserServiceDetailImpl implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load username :{}", username);
        UserResponse response = new UserResponse();
        response.setId(1);
        response.setUserAge(20);
        response.setUserName("knife");
        response.setPassword(passwordEncoder.encode("123456"));
        return new UserInfo(response);
    }
}
