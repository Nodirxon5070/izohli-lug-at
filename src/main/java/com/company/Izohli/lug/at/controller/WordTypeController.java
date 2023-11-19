package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.WordTypeDto;
import com.company.Izohli.lug.at.service.WordTypeService;
import com.company.Izohli.lug.at.utill.SimpleRequestCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Izohli.lug.at.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "wordType")
public class WordTypeController implements SimpleRequestCrud<Integer, RequestWordTypeDto, WordTypeDto> {
    private final WordTypeService wordTypeService;
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<WordTypeDto>> createEntity(@RequestBody @Valid RequestWordTypeDto entity) {
        return convertStatusCodeByData(this.wordTypeService.createEntity(entity));
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseDto<WordTypeDto>> getEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.wordTypeService.getEntity(entityId));
    }

    @Override
    @PutMapping
    public ResponseEntity<ResponseDto<WordTypeDto>> updateEntity(@RequestParam(value = "id")Integer entityId,
                                                                 @RequestBody @Valid   RequestWordTypeDto entity) {
        return convertStatusCodeByData(this.wordTypeService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ResponseDto<WordTypeDto>> deleteEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.wordTypeService.deleteEntity(entityId));
    }
}
