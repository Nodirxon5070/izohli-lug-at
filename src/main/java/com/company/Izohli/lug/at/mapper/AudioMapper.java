package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.module.Audio;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class AudioMapper {

   /* @Mapping(target = "audioId",ignore = true)*/
    public abstract Audio toEntity(RequestAudioDto dto);

    public abstract AudioDto toDto(Audio audio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Audio.class)
    public abstract Audio updateAudio(RequestAudioDto dto, @MappingTarget Audio audio);
}
