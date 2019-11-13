package com.example.authority.controller;

import com.example.authority.rpc.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private UserClient userClient;

    @PostMapping("/hello")
    public Object helloWorld(@RequestParam("nickName") String nickName) {
        return userClient.sayHello(nickName);
    }

}
