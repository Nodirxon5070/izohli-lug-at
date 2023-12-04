package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.module.Word;
import com.company.Izohli.lug.at.repository.*;
import com.company.Izohli.lug.at.service.AudioService;
import com.company.Izohli.lug.at.service.CategoryService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class WordMapper {

    @Autowired
    @Lazy
    protected AudioMapper audioMapper;
    @Autowired
    @Lazy
    protected AudioRepository audioRepository;
    @Autowired
    @Lazy
    protected CategoryMapper categoryMapper;

    @Autowired
    @Lazy
    protected CategoryRepository categoryRepository;

    @Autowired
    @Lazy
    protected WordTypeRepository wordTypeRepository;
    @Autowired
    @Lazy
    protected WordTypeMapper wordTypeMapper;
    @Autowired
    @Lazy
    protected NoteRepository noteRepository;
    @Autowired
    @Lazy
    protected NoteMapper noteMapper;
    @Autowired
    @Lazy
    protected WordInSentenceRepository wordInSentenceRepository;
    @Autowired
    @Lazy
    protected WordInSentenceMapper wordInSentenceMapper;
    @Autowired
    @Lazy
    protected DayWordMapper dayWordMapper;
    @Autowired
    @Lazy
    protected DayWordRepository dayWordRepository;


    @Mapping(target = "wordType",ignore = true)
    @Mapping(target = "notes",ignore = true)
    @Mapping(target = "wordInSentences",ignore = true)
    @Mapping(target = "dayWords",ignore = true)
    public abstract Word toEntity(RequestWordDto dto);

    @Mapping(target = "audio",ignore = true)
    @Mapping(target = "category",ignore = true)
    @Mapping(target = "wordType",ignore = true)
    @Mapping(target = "notes",ignore = true)
    @Mapping(target = "wordInSentences",ignore = true)
    @Mapping(target = "dayWords",ignore = true)
    public abstract WordDto toDto(Word word);

    @Mapping(target = "audio",expression = "java(this.audioMapper.toDto(this.audioRepository.findBYAudioId(word.getAudioId())))")
    @Mapping(target = "category",expression = "java(this.categoryMapper.toDto(this.categoryRepository.findBYCategoryId(word.getCategoryId())))")
    @Mapping(target = "wordType",expression = "java(this.wordTypeRepository.findAllByWordId(word.getWordId()).stream().map(this.wordTypeMapper::toDto).collect(Collectors.toSet()))")
    @Mapping(target = "notes",expression = "java(this.noteRepository.findAllByWordId(word.getWordId()).stream().map(this.noteMapper::toDto).collect(Collectors.toSet()))")
    @Mapping(target = "wordInSentences",expression = "java(this.wordInSentenceRepository.findAllByWordId(word.getWordId()).stream().map(this.wordInSentenceMapper::toDto).collect(Collectors.toSet()))")
    @Mapping(target = "dayWords",expression = "java(this.dayWordRepository.findAllByWordId(word.getWordId()).stream().map(this.dayWordMapper::toDto).collect(Collectors.toSet()))")
    public abstract WordDto toDtoWithAll(Word word);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Word.class)
    public abstract Word updateWord(RequestWordDto dto, @MappingTarget Word word);
}
