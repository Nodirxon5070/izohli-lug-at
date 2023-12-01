package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestWordInSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.module.WordInSentence;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class WordInSentenceMapper {



    public abstract WordInSentence toEntity(RequestWordInSentenceDto dto);

    @Mapping(target = "word",ignore = true)
    public abstract WordInSentenceDto toDto(WordInSentence entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = WordInSentence.class)
    public abstract WordInSentence updateWordInSentence(RequestWordInSentenceDto dto, @MappingTarget WordInSentence wordInSentence);
}
