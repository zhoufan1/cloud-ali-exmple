package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Knife
 * @date 2020/1/2 17:12
 * @description
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {
    private String userName;
}
