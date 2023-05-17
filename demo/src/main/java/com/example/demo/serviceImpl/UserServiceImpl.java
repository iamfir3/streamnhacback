package com.example.demo.serviceImpl;
import com.example.demo.dto.AuthDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.ErrorMessage;
import com.example.demo.exception.UserServiceExceptions;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityContants;
import com.example.demo.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public AuthDto getUser(String email) {
        UserEntity userEntity=userRepository.findByEmail(email);
        if(userEntity==null) throw new UserServiceExceptions(ErrorMessage.NOT_VALID_EMAIL.getErrorMessage(), ErrorMessage.NOT_VALID_EMAIL.getStatus());
        AuthDto returnValue=new AuthDto();
        BeanUtils.copyProperties(userEntity,returnValue);
        returnValue.setUserId(userEntity.getId());
        return returnValue;
    }

    @Override
    public AuthDto getUserByAccessToken(String accessToken) {
        accessToken=accessToken.replace(SecurityContants.TOKEN_PREFIX,"");
        UserEntity userEntity=userRepository.findByAccessToken(accessToken);
        if(userEntity==null) throw new UserServiceExceptions(ErrorMessage.TOKEN_INVALID.getErrorMessage(), ErrorMessage.TOKEN_INVALID.getStatus());
        AuthDto returnValue=new AuthDto();
        BeanUtils.copyProperties(userEntity,returnValue);
        returnValue.setUserId(userEntity.getId());
        return returnValue;
    }

    @Override
    public UserEntity getUserById(Long userId) {
        UserEntity returnValue=userRepository.findById(userId).get();
        return returnValue;
    }

    @Override
    public void updateUserById(Long userId, UserEntity userEntity) {
        UserEntity user=userRepository.findById(userId).get();
        userRepository.save(userEntity);
    }

    @Override
    public AuthDto updateUserByIdWithImage(Long userId, AuthDto authDto) {
        UserEntity user=userRepository.findById(userId).get();
        user.setUsername(authDto.getUsername());
        user.setEmail(authDto.getEmail());
        user.setAvatar(authDto.getAvatar());
        UserEntity storedUser=userRepository.save(user);
        AuthDto returnValue=new AuthDto();
        BeanUtils.copyProperties(storedUser,returnValue);
        return returnValue;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


}
