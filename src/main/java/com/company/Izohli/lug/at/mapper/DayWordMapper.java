package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestDayWordDto;
import com.company.Izohli.lug.at.dto.responseDto.DayWordDto;
import com.company.Izohli.lug.at.module.DayWord;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class DayWordMapper {

    @Autowired
    private WordMapper wordMapper;

    public abstract DayWord toEntity(RequestDayWordDto dto);

    @Mapping(target = "word",ignore = true)
    public abstract DayWordDto toDto(DayWord dayWord);

    @Mapping(target = "word",expression = "java(this.wordMapper.toDto(dayWord.getWord()))")
    public abstract DayWordDto toDtoWithWord(DayWord dayWord);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = DayWord.class)
    public abstract DayWord updateDayWord(RequestDayWordDto dto, @MappingTarget DayWord dayWord);
}
