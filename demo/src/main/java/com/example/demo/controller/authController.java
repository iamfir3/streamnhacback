package com.example.demo.controller;

import com.example.demo.dto.AuthDto;
import com.example.demo.response.AuthResponse;
import com.example.demo.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.AuthRequest;

@RestController()
@RequestMapping("auth")
public class authController {
    @Autowired
    AuthService authService;
    @CrossOrigin
    @PostMapping
    public AuthResponse register(@RequestBody AuthRequest authDetail){
        AuthResponse returnValue=new AuthResponse();
        AuthDto authDto=new AuthDto();
        BeanUtils.copyProperties(authDetail,authDto);
        AuthDto storedUser= authService.createUser(authDto);
        BeanUtils.copyProperties(storedUser,returnValue);
        return returnValue;
    }
}
