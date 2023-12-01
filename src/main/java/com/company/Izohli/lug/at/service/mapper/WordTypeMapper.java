package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestWordTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.WordTypeDto;
import com.company.Izohli.lug.at.module.WordType;
import com.company.Izohli.lug.at.service.TypeService;
import com.company.Izohli.lug.at.service.WordService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class WordTypeMapper {

     @Autowired
     @Lazy
     protected WordService wordService;

     @Autowired
     @Lazy
     protected TypeService typeService;

     public abstract WordType toEntity(RequestWordTypeDto dto);


     @Mapping(target = "word",ignore = true)
     @Mapping(target = "type",ignore = true)
     public abstract WordTypeDto toDto(WordType wordType);


     public void view(){
          WordType wordType = new WordType();
          WordTypeDto wordTypeDto =  new WordTypeDto();

          wordTypeDto.setType(this.typeService.getEntity(wordType.getWordTypeId()).getData());
          wordTypeDto.setWord(this.wordService.getEntity(wordType.getWordId()).getData());

     }
     
     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = WordType.class)
     public abstract WordType updateWordType(RequestWordTypeDto dto, @MappingTarget WordType wordType);
}
