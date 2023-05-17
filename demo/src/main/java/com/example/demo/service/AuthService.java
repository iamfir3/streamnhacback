package com.example.demo.service;

import com.example.demo.dto.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    AuthDto createUser(AuthDto authDto);

    AuthDto resetPassword(AuthDto authDto);
}
