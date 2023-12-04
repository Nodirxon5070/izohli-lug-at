package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.module.Audio;
import com.company.Izohli.lug.at.repository.WordRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class AudioMapper {
    @Lazy
    @Autowired
    protected WordMapper wordMapper;
    @Lazy
    @Autowired
    protected WordRepository wordRepository;


    @Mapping(target = "word",ignore = true)
    public abstract AudioDto toDto(Audio audio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Audio.class)
    public abstract Audio updateAudio(RequestAudioDto dto, @MappingTarget Audio audio);

    @Mapping(target = "word",expression = "java(this.wordMapper.toDto(this.wordRepository.findByAudioId(audio.getAudioId())))")
    public abstract AudioDto toDtoWithWord(Audio audio);

}
