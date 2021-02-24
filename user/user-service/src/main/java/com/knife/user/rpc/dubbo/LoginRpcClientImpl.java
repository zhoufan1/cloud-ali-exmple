package com.knife.user.rpc.dubbo;

import com.knife.user.api.LoginRpcClient;
import com.knife.user.dto.request.UserRequest;
import com.knife.user.dto.response.UserResponse;
import org.apache.dubbo.config.annotation.Service;

@Service
public class LoginRpcClientImpl implements LoginRpcClient {

    @Override
    public UserResponse login(UserRequest user) {
        UserResponse response = new UserResponse();
        response.setId(1);
        response.setUserName("knife");
        response.setUserAge(20);
        response.setMobile("13020188016");
        response.setPassword("123456");
        return null;
    }
}
