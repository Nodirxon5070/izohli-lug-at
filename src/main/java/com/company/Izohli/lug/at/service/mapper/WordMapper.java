package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.module.Word;
import com.company.Izohli.lug.at.repository.DayWordRepository;
import com.company.Izohli.lug.at.repository.NoteRepository;
import com.company.Izohli.lug.at.repository.WordInSentenceRepository;
import com.company.Izohli.lug.at.repository.WordTypeRepository;
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
    protected AudioService audioService;
    @Autowired
    @Lazy
    protected CategoryService categoryService;
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


    public void view(){
        WordDto wordDto = new WordDto();
        Word word = new Word();
        wordDto.setAudio(this.audioService.downloadAudio(word.getAudioId()).getData());
        wordDto.setCategory(this.categoryService.getEntity(word.getCategoryId()).getData());
        wordDto.setWordType(this.wordTypeRepository.findByWordIdAndDeletedAtIsNull(word.getWordId()).stream().map(wordTypeMapper::toDto).collect(Collectors.toSet()));

    }


    @Mapping(target = "audio",expression = "java(this.audioService.downloadAudio(word.getAudioId()).getData())")
    @Mapping(target = "category",expression = "java(this.categoryService.getEntity(word.getCategoryId()).getData())")
    @Mapping(target = "wordType",expression = "java(this.wordTypeRepository.findByWordIdAndDeletedAtIsNull(word.getWordId()).stream().map(this.wordTypeMapper::toDto).collect(Collectors.toSet()))")
    @Mapping(target = "notes",expression = "java(this.noteRepository.findAllByWordIdAndDeletedAtIsNull(word.getWordId()).stream().map(this.noteMapper::toDto).collect(Collectors.toSet()))")
    @Mapping(target = "wordInSentences",expression = "java(this.wordInSentenceRepository.findAllByWordIdAndDeletedAtIsNull(word.getWordId()).stream().map(this.wordInSentenceMapper::toDto).collect(Collectors.toSet()))")
    @Mapping(target = "dayWords",expression = "java(this.dayWordRepository.findAllByWordIdAndDeletedAtIsNull(word.getWordId()).stream().map(this.dayWordMapper::toDto).collect(Collectors.toSet()))")
    public abstract WordDto toDtoWithAll(Word word);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Word.class)
    public abstract Word updateWord(RequestWordDto dto, @MappingTarget Word word);
}
