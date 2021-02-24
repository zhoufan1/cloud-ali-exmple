package com.knife.authority.rpc.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author knife
 * @date 2019/6/13 09:56
 * @description
 */
@FeignClient("user-service")
@RequestMapping("/userInfo")
public interface UserClient {
    @PostMapping("/sayHello")
    Object sayHello(@RequestParam("name") String name);
}
