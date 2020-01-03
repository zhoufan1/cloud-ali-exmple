package com.user.api;

import com.model.UserResponse;

/**
 * @author Knife
 * @date 2020/1/2 16:38
 * @description
 */
public interface UserDubboClient {
    UserResponse sayHello(String name);
}
