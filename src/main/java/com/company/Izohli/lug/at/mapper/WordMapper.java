package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestTypeDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.responseDto.TypeDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.module.Type;
import com.company.Izohli.lug.at.module.Word;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class WordMapper {

    @Autowired
    protected AudioMapper audioMapper;
    @Autowired
    protected  CategoryMapper categoryMapper;
    @Autowired
    protected  DayWordMapper dayWordMapper;
    @Autowired
    protected  WordTypeMapper wordTypeMapper;
    @Autowired
    protected  NoteMapper noteMapper;
    @Autowired
    protected  WordInSentenceMapper wordInSentenceMapper;

    public abstract Word toEntity(RequestWordDto dto);

/*    @Mapping(target = "wordType",ignore = true)
    @Mapping(target = "notes",ignore = true)
    @Mapping(target = "wordInSentences",ignore = true)
    @Mapping(target = "dayWords",ignore = true)*/
    public abstract WordDto toDto(Word word);

    @Mapping(target = "audio",ignore = true)
    @Mapping(target = "category",ignore = true)
    public abstract WordDto toDtoNotCategoryAndAudio(Word word);


    public  void view(){
        Word word = new Word();
        WordDto wordDto = new WordDto();

        wordDto.setAudio(this.audioMapper.toDto(word.getAudio()));
    }

    @Mapping(target = "audio",expression = "java(this.audioMapper.toDto(word.getAudio()))")
    @Mapping(target = "category",expression = "java()this.categoryMapper.toDto(word.getCategory())")
    @Mapping(target = "wordType",expression = "java(word.getWordType().stream().map(this.wordTypeMapper::toDto).collect(Collectors.toSet()))")
    @Mapping(target = "notes",expression = "java(word.getNotes().stream().map(this.noteMapper::toDto).collect(Collectors.toSet()))")
    @Mapping(target = "WordInSentences",expression = "java(word.getWordInSentences().stream().map(this.wordInSentencesMapper::toDto).collect(Collectors.toSet()))")
    @Mapping(target = "dayWords",expression = "java(word.getDayWords().stream().map(this.dayWordMapper::toDto).collect(Collectors.toSet()))")
    public abstract WordDto toDtoWithAll(Word word);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Word.class)
    public abstract Word updateWord(RequestWordDto dto, @MappingTarget Word word);
}
