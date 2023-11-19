package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.utill.SimpleRequestCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "audio")
public class AudioController implements SimpleRequestCrud<Integer, RequestAudioDto, AudioDto> {
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<AudioDto>> createEntity(RequestAudioDto entity) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<AudioDto>> getEntity(Integer entityId) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<AudioDto>> updateEntity(Integer entityId, RequestAudioDto entity) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<AudioDto>> deleteEntity(Integer entityId) {
        return null;
    }
}
