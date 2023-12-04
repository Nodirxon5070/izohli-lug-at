package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestNoteDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.module.Note;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.service.WordService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class NoteMapper {

    @Autowired
    @Lazy
    protected WordMapper wordMapper;


    @Lazy
    @Autowired
    protected WordRepository wordRepository;

    public abstract Note toEntity(RequestNoteDto dto);

    @Mapping(target = "word",ignore = true)
    public abstract NoteDto toDto(Note note);

    @Mapping(target = "word",expression = "java(this.wordMapper.toDto(this.wordRepository.findWordBYWordId(note.getWordId())))")
    public abstract NoteDto toDtoWithWord(Note note);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Note.class)
    public abstract Note updateNote(RequestNoteDto dto, @MappingTarget Note note);
}
