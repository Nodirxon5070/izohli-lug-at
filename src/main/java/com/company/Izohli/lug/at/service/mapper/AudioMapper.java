package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.module.Audio;
import org.mapstruct.*;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class AudioMapper {


    @Mapping(target = "word",ignore = true)
    public abstract AudioDto toDto(Audio audio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Audio.class)
    public abstract Audio updateAudio(RequestAudioDto dto, @MappingTarget Audio audio);

    /*public void view(){
     Audio audio = new Audio();
     AudioDto audioDto = new AudioDto();
     audioDto.setWords(audio.getWords().stream().map(wordMapper::toDtoNotCategoryAndAudio).collect(Collectors.toSet()));
    }*/
    //Usage only get
//    @Mapping(target = "words",expression = "java(audio.getWords().stream().map(wordMapper::toDtoNotCategoryAndAudio).collect(Collectors.toSet()))")

}
