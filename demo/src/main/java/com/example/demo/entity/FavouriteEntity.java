package com.example.demo.entity;

import com.example.demo.compositekey.favouriteid;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@IdClass(favouriteid.class)
public class FavouriteEntity {

    @Id
    private Long userId;

    @Id
    private Long musicId;

    @ManyToOne
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="musicId",insertable = false,updatable = false)
    private MusicEntity musicEntity;
}
