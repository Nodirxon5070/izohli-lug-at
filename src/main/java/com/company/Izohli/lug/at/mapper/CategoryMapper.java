package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestCategoryDto;
import com.company.Izohli.lug.at.dto.responseDto.CategoryDto;
import com.company.Izohli.lug.at.module.Audio;
import com.company.Izohli.lug.at.module.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class CategoryMapper {

    public abstract Category toEntity(RequestCategoryDto dto);

    public abstract CategoryDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Category.class)
    public abstract Category updateCategory(RequestCategoryDto dto, @MappingTarget Category category);
}
