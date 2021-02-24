package com.knife.user.api;


import com.knife.user.dto.response.UserResponse;

/**
 * @author Knife
 * @date 2020/1/2 16:38
 * @description
 */
public interface UserDubboClient {
    UserResponse sayHello(String name);
}
