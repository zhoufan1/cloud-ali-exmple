package com.knife.authority.controller;

import com.knife.authority.rpc.dubbo.UserDubboServcie;
import com.knife.authority.rpc.fegin.UserClient;
import com.knife.foundation.dto.Response;
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
    public Object helloWorld(@RequestParam("name") String name) {
        return userClient.sayHello(name);
    }

    @PostMapping("/hello/v1")
    public Object helloWorldV1(@RequestParam("name") String name) {
        return userDubboServcie.sayHello(name);
    }

    @PostMapping("test")
    public Response<String> test(@RequestParam("name")String name){
        return Response.success(name);
    }
}
