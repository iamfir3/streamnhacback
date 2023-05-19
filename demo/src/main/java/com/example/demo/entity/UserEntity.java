package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="username",nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="avatar")
    private String avatar;

    @Column(name="accessToken")
    private String accessToken;

    @Column(name="email",nullable = false)
    private String email;

    @OneToMany(mappedBy = "userEntity")
    private List<FavouriteEntity> favouriteEntityList=new ArrayList<>();
}
