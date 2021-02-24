package com.knife.user.controller;

import com.knife.user.dto.response.UserResponse;
import com.knife.foundation.dto.Response;
import com.knife.user.model.User;
import com.knife.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author knife
 * @date 2019/6/13 10:08
 * @description
 */
@RestController
@RequestMapping("/authorityInfo")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response<UserResponse> login(@RequestBody User user) {
       /* User login = userService.login(user);
        if (login == null) {
            throw new BusinessException(BusinessCode.AuthorityAccount);
        }
        BeanUtils.copyProperties(user, response);*/
        UserResponse response = new UserResponse();
        response.setId(1);
        response.setUserName("knife");
        response.setUserAge(20);
        response.setMobile("13020188016");
        response.setPassword("123456");
        return Response.success(response);
    }
}
