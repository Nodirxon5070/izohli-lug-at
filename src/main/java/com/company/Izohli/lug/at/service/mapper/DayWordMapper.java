package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestDayWordDto;
import com.company.Izohli.lug.at.dto.responseDto.DayWordDto;
import com.company.Izohli.lug.at.module.DayWord;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.service.WordService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class DayWordMapper {

    @Autowired
    @Lazy
    protected WordMapper wordMapper;

    @Lazy
    @Autowired
    protected WordRepository wordRepository;

    public abstract DayWord toEntity(RequestDayWordDto dto);

    @Mapping(target = "word",ignore = true)
    public abstract DayWordDto toDto(DayWord dayWord);

    @Mapping(target = "word",expression = "java(this.wordMapper.toDto(this.wordRepository.findWordBYWordId(dayWord.getWordId())))")
    public abstract DayWordDto toDtoWithWord(DayWord dayWord);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = DayWord.class)
    public abstract DayWord updateDayWord(RequestDayWordDto dto, @MappingTarget DayWord dayWord);
}
