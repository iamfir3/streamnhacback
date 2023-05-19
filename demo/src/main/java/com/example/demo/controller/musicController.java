package com.example.demo.controller;


import com.example.demo.dto.FavouriteDto;
import com.example.demo.dto.MusicDto;
import com.example.demo.entity.FavouriteEntity;
import com.example.demo.entity.MusicEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.FavouriteRepository;
import com.example.demo.repository.MusicRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/music")
@RestController
public class musicController {

    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavouriteRepository favouriteRepository;
    @GetMapping
    public List<MusicDto> GetAllMusic (){
        List<MusicDto> returnValue = new ArrayList<>();
        List<MusicEntity> musicEntities = musicRepository.findAll();
        for(MusicEntity musicEntity:musicEntities)
        {
            MusicDto musicDto=new MusicDto();
            BeanUtils.copyProperties(musicEntity,musicDto);
            returnValue.add(musicDto);
        }
        return  returnValue;
    }

    @PostMapping
    public String addFavourite(@RequestParam(name="userId") Long userId, @RequestParam(name="musicId") Long musicId){
        UserEntity userEntity=userRepository.findById(userId).get();
        MusicEntity musicEntity=musicRepository.findById(musicId).get();
        FavouriteEntity favouriteEntity=new FavouriteEntity();
        favouriteEntity.setMusicEntity(musicEntity);
        favouriteEntity.setMusicId(musicId);
        favouriteEntity.setUserId(userId);
        favouriteEntity.setUserEntity(userEntity);
        FavouriteEntity saved=favouriteRepository.save(favouriteEntity);
        return "done";
    }

    @GetMapping("/favourite/{userId}")
    public List<FavouriteDto> GetAllFavourite(@PathVariable Long userId){
        List<FavouriteDto> returnValue = new ArrayList<>();
        List<FavouriteEntity> favouriteEntityList = favouriteRepository.findAllByUserId(userId);
        for(FavouriteEntity favouriteEntity:favouriteEntityList)
        {
            FavouriteDto favouriteDto=new FavouriteDto();
            BeanUtils.copyProperties(favouriteEntity,favouriteDto);
            returnValue.add(favouriteDto);
        }
        return  returnValue;
    }
}
