package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.SentenceDto;
import com.company.Izohli.lug.at.service.SentenceService;
import com.company.Izohli.lug.at.utill.SimpleRequestCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Izohli.lug.at.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value ="sentence" )
public class SentenceController implements SimpleRequestCrud<Integer, RequestSentenceDto, SentenceDto> {
    private final SentenceService sentenceService;
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<SentenceDto>> createEntity(@RequestBody @Valid RequestSentenceDto entity) {
        return convertStatusCodeByData(this.sentenceService.createEntity(entity));
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseDto<SentenceDto>> getEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.sentenceService.getEntity(entityId));
    }

    @Override
    @PutMapping
    public ResponseEntity<ResponseDto<SentenceDto>> updateEntity(@RequestParam(value = "id")Integer entityId,
                                                                 @RequestBody @Valid  RequestSentenceDto entity) {
        return convertStatusCodeByData(this.sentenceService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ResponseDto<SentenceDto>> deleteEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.sentenceService.deleteEntity(entityId));
    }
}
