package com.example.user.rpc.dubbo;

import com.model.UserResponse;
import com.user.api.UserDubboClient;
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
