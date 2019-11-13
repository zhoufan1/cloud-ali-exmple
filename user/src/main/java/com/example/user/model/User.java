package com.example.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

//@Entity
//@Table(name = "T_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "ID")
    private Integer id;
//    @Column(name = "USER_NAME")
    private String userName;
//    @Column(name = "USER_PASS")
    private Integer userPass;
//    @Column(name = "USER_AGE")
    private Integer userAge;
//
//
//
}
