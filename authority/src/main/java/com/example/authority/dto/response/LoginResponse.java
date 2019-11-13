package com.example.authority.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoufan
 * @date 2019/6/13 09:59
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private Integer Id;
    private String userName;
    private String userSex;
}
