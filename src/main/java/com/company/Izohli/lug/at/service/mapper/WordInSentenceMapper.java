package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestWordInSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.module.WordInSentence;
import com.company.Izohli.lug.at.repository.SentenceRepository;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.service.WordService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class WordInSentenceMapper {

    @Lazy
    @Autowired
    protected WordMapper wordMapper;

    @Lazy
    @Autowired
    protected WordRepository wordRepository;

    @Lazy
    @Autowired
    protected SentenceMapper sentenceMapper;

    @Lazy
    @Autowired
    protected SentenceRepository sentenceRepository;


    public abstract WordInSentence toEntity(RequestWordInSentenceDto dto);

    @Mapping(target = "word", ignore = true)
    @Mapping(target = "sentence", ignore = true)
    public abstract WordInSentenceDto toDto(WordInSentence entity);

    @Mapping(target = "sentence", expression = "java(this.sentenceMapper.toDto(this.sentenceRepository.findBYSentenceId(entity.getSentenceId())))")
    @Mapping(target = "word", expression = "java(this.wordMapper.toDto(this.wordRepository.findWordBYWordId(entity.getWordId())))")
    public abstract WordInSentenceDto toDtoWithWordsAndSentence(WordInSentence entity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = WordInSentence.class)
    public abstract WordInSentence updateWordInSentence(RequestWordInSentenceDto dto, @MappingTarget WordInSentence wordInSentence);
}
