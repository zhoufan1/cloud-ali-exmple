package com.example.foundation.authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthoritySession {
    public static final String X_AUTHORITY_HEADER = "X-Authority";
    private Integer Id;
    private String version;
    private String clientIp;
    private Long clientTime;

}
