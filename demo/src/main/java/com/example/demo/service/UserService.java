package com.example.demo.service;

import com.example.demo.dto.AuthDto;
import com.example.demo.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AuthDto getUser(String email);
    AuthDto getUserByAccessToken(String accessToken);

    UserEntity getUserById(Long userId);

    void updateUserById(Long userId, UserEntity userEntity);

    AuthDto updateUserByIdWithImage(Long userId, AuthDto authDto);
}
