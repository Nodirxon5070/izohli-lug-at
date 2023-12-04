package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.SentenceDto;
import com.company.Izohli.lug.at.module.Sentence;
import com.company.Izohli.lug.at.repository.WordInSentenceRepository;
import com.company.Izohli.lug.at.service.WordInSentenceService;
import com.company.Izohli.lug.at.service.WordService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class SentenceMapper {

    @Lazy
    @Autowired
    protected WordInSentenceMapper wordInSentenceMapper;

    @Lazy
    @Autowired
    protected WordInSentenceRepository wordInSentenceRepository;

    public abstract Sentence toEntity(RequestSentenceDto dto);

    @Mapping(target = "wordInSentences",ignore = true)
    public abstract SentenceDto toDto(Sentence sentence);

    @Mapping(target = "wordInSentences",expression = "java(this.wordInSentenceMapper.toDto(this.wordInSentenceRepository.findBySentenceId(entity.getSentenceId())))")
    public abstract SentenceDto toDtoWithWordInSentence(Sentence entity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Sentence.class)
    public abstract Sentence updateSentence(RequestSentenceDto dto, @MappingTarget Sentence sentence);
}
