package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestTypeDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.responseDto.TypeDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.module.Type;
import com.company.Izohli.lug.at.module.Word;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class WordMapper {
    public abstract Word toEntity(RequestWordDto dto);

    @Mapping(target = "wordType",ignore = true)
    @Mapping(target = "notes",ignore = true)
    @Mapping(target = "wordInSentences",ignore = true)
    @Mapping(target = "dayWords",ignore = true)
    public abstract WordDto toDto(Word word);

    public abstract WordDto toDtoWithAll(Word word);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Word.class)
    public abstract Word updateWord(RequestWordDto dto, @MappingTarget Word word);
}
