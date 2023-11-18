package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordInSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.module.Audio;
import com.company.Izohli.lug.at.module.WordInSentence;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class WordInSentenceMapper {
    public abstract WordInSentence toEntity(RequestWordInSentenceDto dto);

    public abstract WordInSentenceDto toDto(WordInSentence wordInSentence);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = WordInSentence.class)
    public abstract Audio updateWordInSentence(RequestWordInSentenceDto dto, @MappingTarget WordInSentence wordInSentence);
}
