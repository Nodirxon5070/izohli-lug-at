package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestWordTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.WordTypeDto;
import com.company.Izohli.lug.at.module.WordType;
import com.company.Izohli.lug.at.repository.TypeRepository;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.service.TypeService;
import com.company.Izohli.lug.at.service.WordService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class WordTypeMapper {

     @Lazy
     @Autowired
     protected WordMapper wordMapper;

     @Lazy
     @Autowired
     protected WordRepository wordRepository;

     @Lazy
     @Autowired
     protected TypeMapper typeMapper;

     @Lazy
     @Autowired
     protected TypeRepository typeRepository;

     public abstract WordType toEntity(RequestWordTypeDto dto);


     @Mapping(target = "word",ignore = true)
     @Mapping(target = "type",ignore = true)
     public abstract WordTypeDto toDto(WordType wordType);


     public void view(){
          WordType wordType = new WordType();
          WordTypeDto wordTypeDto =  new WordTypeDto();

          wordTypeDto.setType(this.typeMapper.toDto(this.typeRepository.findTypeBYTypeId(wordType.getTypeId())));
          wordTypeDto.setWord(this.wordMapper.toDto(this.wordRepository.findWordBYWordId(wordType.getWordId())));

     }

     @Mapping(target = "word",expression = "java(this.wordMapper.toDto(this.wordRepository.findWordBYWordId(wordType.getWordId())))")
     @Mapping(target = "type",expression = "java(this.typeMapper.toDto(this.typeRepository.findTypeBYTypeId(wordType.getTypeId())))")
     public abstract WordTypeDto toDtoWithWordAndType(WordType wordType);
     
     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = WordType.class)
     public abstract WordType updateWordType(RequestWordTypeDto dto, @MappingTarget WordType wordType);
}
