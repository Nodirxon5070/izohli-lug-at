package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.SentenceDto;
import com.company.Izohli.lug.at.utill.SimpleCrud;

public class SentenceService implements SimpleCrud<Integer, RequestSentenceDto, SentenceDto> {
    @Override
    public ResponseDto<SentenceDto> createEntity(RequestSentenceDto entity) {
        return null;
    }

    @Override
    public ResponseDto<SentenceDto> getEntity(Integer entityId) {
        return null;
    }

    @Override
    public ResponseDto<SentenceDto> updateEntity(Integer entityId, RequestSentenceDto entity) {
        return null;
    }

    @Override
    public ResponseDto<SentenceDto> deleteEntity(Integer entityId) {
        return null;
    }
}
