package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.service.WordService;
import com.company.Izohli.lug.at.utill.SimpleRequestCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Izohli.lug.at.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "word")
public class WordController implements SimpleRequestCrud<Integer, RequestWordDto, WordDto> {
    private final WordService wordService;
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<WordDto>> createEntity(@RequestBody @Valid RequestWordDto entity) {
        return convertStatusCodeByData(this.wordService.createEntity(entity));
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseDto<WordDto>> getEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.wordService.getEntity(entityId));
    }

    @Override
    @PutMapping
    public ResponseEntity<ResponseDto<WordDto>> updateEntity(@RequestParam(value = "id")Integer entityId,
                                                             @RequestBody @Valid  RequestWordDto entity) {
        return convertStatusCodeByData(this.wordService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ResponseDto<WordDto>> deleteEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.wordService.deleteEntity(entityId));
    }
}
