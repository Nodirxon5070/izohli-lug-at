package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.utill.SimpleCrud;

public class WordService implements SimpleCrud<Integer, RequestWordDto, WordDto> {
    @Override
    public ResponseDto<WordDto> createEntity(RequestWordDto entity) {
        return null;
    }

    @Override
    public ResponseDto<WordDto> getEntity(Integer entityId) {
        return null;
    }

    @Override
    public ResponseDto<WordDto> updateEntity(Integer entityId, RequestWordDto entity) {
        return null;
    }

    @Override
    public ResponseDto<WordDto> deleteEntity(Integer entityId) {
        return null;
    }
}
