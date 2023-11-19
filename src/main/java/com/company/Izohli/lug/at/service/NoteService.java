package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestNoteDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.utill.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService implements SimpleCrud<Integer, RequestNoteDto, NoteDto> {
    @Override
    public ResponseDto<NoteDto> createEntity(RequestNoteDto entity) {
        return null;
    }

    @Override
    public ResponseDto<NoteDto> getEntity(Integer entityId) {
        return null;
    }

    @Override
    public ResponseDto<NoteDto> updateEntity(Integer entityId, RequestNoteDto entity) {
        return null;
    }

    @Override
    public ResponseDto<NoteDto> deleteEntity(Integer entityId) {
        return null;
    }
}
