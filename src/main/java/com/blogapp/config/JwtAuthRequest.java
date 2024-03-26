package com.blogapp.config;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String email;
    private String password;
}
