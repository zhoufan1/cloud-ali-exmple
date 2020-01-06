package com.example.authority.dto.request;

import lombok.Data;

/**
 * @author knife
 * @date 2019/6/13 09:58
 * @description
 */
@Data
public class LoginRequest {
    private String userName;
    private String userPass;
}
