package com.company.Izohli.lug.at.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestNoteDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.dto.responseDto.SentenceDto;
import com.company.Izohli.lug.at.module.Note;
import com.company.Izohli.lug.at.module.Sentence;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class SentenceMapper {
    public abstract Sentence toEntity(RequestSentenceDto dto);

    public abstract SentenceDto toDto(Sentence sentence);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Sentence.class)
    public abstract Sentence updateSentence(RequestSentenceDto dto, @MappingTarget Sentence sentence);
}
