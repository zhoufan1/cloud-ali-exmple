package com.example.user.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZhouFan
 * @date 2019/10/31 15:14
 * @description
 */
@FeignClient("provider")
@RequestMapping("/user")
public interface UserServiceFacede {
    @GetMapping("/helloWorld")
    String helloWorld(String name);
}
