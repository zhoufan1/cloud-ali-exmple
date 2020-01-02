package com.example.authority.controller;

import com.example.authority.rpc.dubbo.UserDubboServcie;
import com.example.authority.rpc.fegin.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MyController {
    private final UserClient userClient;
    private final UserDubboServcie userDubboServcie;

    @PostMapping("/hello")
    public Object helloWorld(@RequestParam("nickName") String nickName) {
        return userClient.sayHello(nickName);
    }

    @PostMapping("/hello/v1")
    public Object helloWorldV1(@RequestParam("nickName") String nickName) {
        return userDubboServcie.sayHello(nickName);
    }
}
