package com.example.demo.repository;

import com.example.demo.compositekey.favouriteid;
import com.example.demo.entity.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository <FavouriteEntity, favouriteid>{

    List<FavouriteEntity> findAllByUserId(Long userId);
}
