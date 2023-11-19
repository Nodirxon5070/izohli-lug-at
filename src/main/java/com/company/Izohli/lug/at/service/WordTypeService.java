package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.WordTypeDto;
import com.company.Izohli.lug.at.utill.SimpleCrud;
import jakarta.persistence.SecondaryTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordTypeService implements SimpleCrud<Integer, RequestWordTypeDto, WordTypeDto> {
    @Override
    public ResponseDto<WordTypeDto> createEntity(RequestWordTypeDto entity) {
        return null;
    }

    @Override
    public ResponseDto<WordTypeDto> getEntity(Integer entityId) {
        return null;
    }

    @Override
    public ResponseDto<WordTypeDto> updateEntity(Integer entityId, RequestWordTypeDto entity) {
        return null;
    }

    @Override
    public ResponseDto<WordTypeDto> deleteEntity(Integer entityId) {
        return null;
    }
}
