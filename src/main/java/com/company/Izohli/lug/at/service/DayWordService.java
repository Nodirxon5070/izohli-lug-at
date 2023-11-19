package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestDayWordDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.DayWordDto;
import com.company.Izohli.lug.at.utill.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DayWordService implements SimpleCrud<Integer, RequestDayWordDto, DayWordDto> {
    @Override
    public ResponseDto<DayWordDto> createEntity(RequestDayWordDto entity) {
        return null;
    }

    @Override
    public ResponseDto<DayWordDto> getEntity(Integer entityId) {
        return null;
    }

    @Override
    public ResponseDto<DayWordDto> updateEntity(Integer entityId, RequestDayWordDto entity) {
        return null;
    }

    @Override
    public ResponseDto<DayWordDto> deleteEntity(Integer entityId) {
        return null;
    }
}
