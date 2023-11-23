package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestWordInSentenceDto;

import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;

import com.company.Izohli.lug.at.module.WordInSentence;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class WordInSentenceMapper {



    public abstract WordInSentence toEntity(RequestWordInSentenceDto dto);

  //  @Mapping(target = "word",ignore = true)
    public abstract WordInSentenceDto toDto(WordInSentence wordInSentence);

 //   @Mapping(target = "word",expression = "java(this.wordMapper.toDto(dayWord.getWord()))")
    public abstract WordInSentenceDto toDtoWithWord(WordInSentence wordInSentence);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = WordInSentence.class)
    public abstract WordInSentence updateWordInSentence(RequestWordInSentenceDto dto, @MappingTarget WordInSentence wordInSentence);
}
