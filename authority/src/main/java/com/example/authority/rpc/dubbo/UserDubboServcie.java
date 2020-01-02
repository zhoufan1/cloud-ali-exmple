package com.example.authority.rpc.dubbo;

import com.model.UserResponse;
import com.user.api.UserDubboClient;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author Knife
 * @date 2020/1/2 16:38
 * @description
 */
@Service
public class UserDubboServcie {
    @Reference
    private UserDubboClient userDubboClient;

    public UserResponse sayHello(String name) {
        UserResponse userResponse = userDubboClient.sayHello(name);
        return userResponse;
    }

}
