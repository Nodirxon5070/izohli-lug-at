package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestCategoryDto;
import com.company.Izohli.lug.at.dto.responseDto.CategoryDto;
import com.company.Izohli.lug.at.module.Audio;
import com.company.Izohli.lug.at.module.Category;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;

import java.util.stream.Collectors;
@RequiredArgsConstructor
@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class CategoryMapper {

    private final WordMapper wordMapper;

    public abstract Category toEntity(RequestCategoryDto dto);

    @Mapping(target = "words",ignore = true)
    public abstract CategoryDto toDto(Category category);


    @Mapping(target = "words",expression = "java(category.getWords().stream().map(wordMapper::toDtoNotCategoryAndAudio).collect(Collectors.toSet()))")
    public abstract CategoryDto toDtoWithWord(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Category.class)
    public abstract Category updateCategory(RequestCategoryDto dto, @MappingTarget Category category);
}
