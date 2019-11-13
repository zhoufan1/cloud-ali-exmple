package com.example.user.rpc.impl;

import com.example.user.rpc.UserServiceFacede;
import org.springframework.stereotype.Component;

/**
 * @author ZhouFan
 * @date 2019/10/31 15:47
 * @description
 */
//@Service(protocol = "dubbo",interfaceClass = UserServiceFacede.class)
@Component
public class UserServiceFacedeImpl implements UserServiceFacede {
    @Override
    public String helloWorld(String name) {
        return name.concat("hello,world");
    }
}
