package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.dto.responseDto.WordTypeDto;
import com.company.Izohli.lug.at.module.Word;
import com.company.Izohli.lug.at.module.WordType;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class WordTypeMapper {

    @Autowired
    protected WordMapper wordMapper;

    public abstract WordType toEntity(RequestWordTypeDto dto);

    /*@Mapping(target = "word",ignore = true)
    public abstract WordTypeDto toDto(WordType wordType);
*/
    @Mapping(target = "word",expression = "java(this.wordMapper.toDto(dayWord.getWord()))")
    public abstract WordTypeDto toDtoWithWord(WordType wordType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = WordType.class)
    public abstract WordType updateWordType(RequestWordTypeDto dto, @MappingTarget WordType wordType);
}
