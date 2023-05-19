package com.example.demo.compositekey;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class favouriteid implements Serializable {
    private Long userId;

    private Long musicId;
}
