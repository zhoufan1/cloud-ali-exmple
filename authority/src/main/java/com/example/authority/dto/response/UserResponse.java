package com.example.authority.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoufan
 * @date 2019/6/5 10:41
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Integer id;
    private String userName;
    private Integer userAge;
    private String userPass;
}
