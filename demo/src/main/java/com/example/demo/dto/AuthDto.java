package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto implements Serializable {
    public static final long serialVersionUId=1L;

    private String username;
    private Long userId;
    private String avatar;
    private String password;
    private String email;
    private String encryptPassword;
    private String token;
}
