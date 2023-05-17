package com.example.demo.controller;


import com.example.demo.dto.MusicDto;
import com.example.demo.entity.MusicEntity;
import com.example.demo.repository.MusicRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/music")
@RestController
public class musicController {

    @Autowired
    private MusicRepository musicRepository;
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
}
