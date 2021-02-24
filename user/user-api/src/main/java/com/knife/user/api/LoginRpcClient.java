package com.knife.user.api;


import com.knife.user.dto.request.UserRequest;
import com.knife.user.dto.response.UserResponse;


public interface LoginRpcClient {
    UserResponse login(UserRequest user);
}
