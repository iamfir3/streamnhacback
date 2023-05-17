package com.example.demo.serviceImpl;


import com.example.demo.dto.AuthDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.ErrorMessage;
import com.example.demo.exception.UserServiceExceptions;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AuthDto createUser(AuthDto authDto) {
        AuthDto returnValue = new AuthDto();
        UserEntity userEntity = new UserEntity();
        if (userRepository.findByEmail(authDto.getEmail()) != null) {
            throw new UserServiceExceptions(ErrorMessage.EMAIL_ALREADY_EXIST.getErrorMessage(), ErrorMessage.EMAIL_ALREADY_EXIST.getStatus());
        }
        BeanUtils.copyProperties(authDto, userEntity);
        userEntity.setPassword(bCryptPasswordEncoder.encode(authDto.getPassword()));
        userEntity.setUsername(authDto.getUsername());
        UserEntity storedUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(storedUser, returnValue);

        return returnValue;
    }

    @Override
    public AuthDto resetPassword(AuthDto authDto) {
        AuthDto returnValue = new AuthDto();
        UserEntity userEntity = userRepository.findByEmail(authDto.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(authDto.getPassword()));
        UserEntity storedUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(storedUser, returnValue);
        return returnValue;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }
}
