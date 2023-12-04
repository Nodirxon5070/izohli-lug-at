package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.TypeDto;
import com.company.Izohli.lug.at.module.Type;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.repository.WordTypeRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class TypeMapper {

    @Lazy
    @Autowired
    protected WordTypeMapper wordTypeMapper;

    @Lazy
    @Autowired
    protected WordTypeRepository wordTypeRepository;

    public abstract Type toEntity(RequestTypeDto dto);

    @Mapping(target = "wordType",ignore = true)
    public abstract TypeDto toDto(Type type);

    @Mapping(target = "wordType",expression = "java(this.wordTypeMapper.toDto(this.wordTypeRepository.findWordTypeByTypeId(type.getTypeId())))")
    public abstract TypeDto toDtoWithWordType(Type type);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Type.class)
    public abstract Type updateType(RequestTypeDto dto, @MappingTarget Type type);
}
