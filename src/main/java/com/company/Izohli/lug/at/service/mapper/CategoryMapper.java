package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestCategoryDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.CategoryDto;
import com.company.Izohli.lug.at.module.Audio;
import com.company.Izohli.lug.at.module.Category;
import com.company.Izohli.lug.at.repository.WordRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;
@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class CategoryMapper {

    @Lazy
    @Autowired
    protected WordMapper wordMapper;
    @Lazy
    @Autowired
    protected WordRepository wordRepository;

    public abstract Category toEntity(RequestCategoryDto dto);

    @Mapping(target = "words",ignore = true)
    public abstract CategoryDto toDto(Category category);


    private void view(){
        CategoryDto dto = new CategoryDto();
        Category category = new Category();
//        dto.setWords();
    }


    @Mapping(target = "words",expression = "java(this.wordRepository.findAllByCategoryId(category.getCategoryId()).stream().map(this.wordMapper::toDto).collect(Collectors.toSet()))")
    public abstract CategoryDto toDtoWithWord(Category category);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Category.class)
    public abstract Category updateCategory(RequestCategoryDto dto, @MappingTarget Category category);
}
