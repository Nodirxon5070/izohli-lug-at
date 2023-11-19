package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.TypeDto;
import com.company.Izohli.lug.at.utill.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeService implements SimpleCrud<Integer, RequestTypeDto, TypeDto> {
    @Override
    public ResponseDto<TypeDto> createEntity(RequestTypeDto entity) {
        return null;
    }

    @Override
    public ResponseDto<TypeDto> getEntity(Integer entityId) {
        return null;
    }

    @Override
    public ResponseDto<TypeDto> updateEntity(Integer entityId, RequestTypeDto entity) {
        return null;
    }

    @Override
    public ResponseDto<TypeDto> deleteEntity(Integer entityId) {
        return null;
    }
}
