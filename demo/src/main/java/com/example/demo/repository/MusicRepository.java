package com.example.demo.repository;

import com.example.demo.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<MusicEntity,Long> {

}
