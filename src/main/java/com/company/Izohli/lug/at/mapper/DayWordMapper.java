package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.module.Audio;
import com.company.Izohli.lug.at.module.DayWord;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class DayWordMapper {

    public abstract DayWord toEntity(RequestDay dto);

    public abstract AudioDto toDto(Audio audio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Audio.class)
    public abstract Audio updateAudio(RequestAudioDto dto, @MappingTarget Audio audio);
}
