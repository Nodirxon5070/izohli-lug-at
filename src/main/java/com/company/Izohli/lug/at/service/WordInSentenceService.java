package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordInSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.utill.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordInSentenceService implements SimpleCrud<Integer, RequestWordInSentenceDto, WordInSentenceDto> {
    @Override
    public ResponseDto<WordInSentenceDto> createEntity(RequestWordInSentenceDto entity) {
        return null;
    }

    @Override
    public ResponseDto<WordInSentenceDto> getEntity(Integer entityId) {
        return null;
    }

    @Override
    public ResponseDto<WordInSentenceDto> updateEntity(Integer entityId, RequestWordInSentenceDto entity) {
        return null;
    }

    @Override
    public ResponseDto<WordInSentenceDto> deleteEntity(Integer entityId) {
        return null;
    }
}
