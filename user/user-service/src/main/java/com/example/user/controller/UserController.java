package com.example.user.controller;

import com.example.foundation.dto.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserController {

    @PostMapping("/sayHello")
    public Response<String> sayHello(@RequestParam("name") String name) {
        return Response.success("hello:" + name);
    }
}
