package com.knife.user.rpc.dubbo;

import com.knife.user.api.UserDubboClient;
import com.knife.user.dto.response.UserResponse;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author Knife
 * @date 2020/1/2 16:56
 * @description
 */
@Service
public class UserDubboClientImpl implements UserDubboClient {
    @Override
    public UserResponse sayHello(String name) {
        return UserResponse.builder().userName(name).build();
    }
}
