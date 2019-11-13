package com.example.authority.config;

import com.example.authority.rpc.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author ZhouFan
 * @date 2019/10/25 16:33
 * @description
 */
@Slf4j
@Component
public class UserServiceDetailImpl implements UserDetailsService {
    @Autowired
    private UserClient userClient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load username :{}",username);
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
        return null;
    }
}
