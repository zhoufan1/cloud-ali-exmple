package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author Knife
 * @date 2020/1/2 17:12
 * @description
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse implements Serializable {
    private String userName;
}
