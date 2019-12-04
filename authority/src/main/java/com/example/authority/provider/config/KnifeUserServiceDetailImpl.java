package com.example.authority.provider.config;

import com.example.authority.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class KnifeUserServiceDetailImpl implements UserDetailsService {
    //    @Autowired
//    private UserClient userClient;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load username :{}", username);
//        Response<UserResponse> user = userClient.findUserByUserName(username);
     /*   UserResponse response = UserResponse.builder().id(1)
                .userName("admin").userPass(new BCryptPasswordEncoder().encode("123456"))
                .userAge(18).build();
        Response<UserResponse> user = Response.success(response);
        if (!user.isSuccess()) {
            throw new BusinessException(user);
        }
        log.info("response:{}", JSON.toJSONString(user));
        return new UserInfo(user.getData());*/
        UserResponse response = new UserResponse();
        response.setId(1);
        response.setUserAge(20);
        response.setUserName("admin");
        response.setUserPass(passwordEncoder.encode("123456"));
        return new UserInfo(response);
    }
}
