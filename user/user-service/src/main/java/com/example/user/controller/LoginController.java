package com.example.user.controller;

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
   /* @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response<UserResponse> login(@RequestBody User user) {
        User login = userService.login(user);
        if (login == null) {
            throw new BusinessException(BusinessCode.AuthorityAccount);
        }
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return Response.success(response);
    }*/
}
