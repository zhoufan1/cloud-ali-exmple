package com.example.authority.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author zhoufan
 * @date 2019/6/5 10:41
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse implements Serializable {
    private Integer id;
    private String userName;
    private Integer userAge;
    private String userPass;
}
