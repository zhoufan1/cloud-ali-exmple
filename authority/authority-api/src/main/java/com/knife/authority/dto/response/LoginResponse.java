package com.knife.authority.dto.response;

import lombok.*;

/**
 * @author knife
 * @date 2019/6/13 09:59
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Integer Id;
    private String userName;
    private String userSex;
}
