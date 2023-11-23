package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestNoteDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.module.Audio;
import com.company.Izohli.lug.at.module.Note;
import org.aspectj.weaver.ast.Not;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class NoteMapper {

    @Autowired
    protected WordMapper wordMapper;
    public abstract Note toEntity(RequestNoteDto dto);

    @Mapping(target = "word",ignore = true)
    public abstract NoteDto toDto(Note note);

    @Mapping(target = "word",expression = "java(this.wordMapper.toDto(note.getWord()))")
    public abstract NoteDto toDtoWithWord(Note note);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Note.class)
    public abstract Note updateNote(RequestNoteDto dto, @MappingTarget Note note);
}
