package com.example.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author knife
 * @date 2019/6/5 10:39
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String userName;
    private String userPass;
}
