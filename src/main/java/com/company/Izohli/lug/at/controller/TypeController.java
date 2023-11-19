package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.TypeDto;
import com.company.Izohli.lug.at.service.TypeService;
import com.company.Izohli.lug.at.utill.SimpleRequestCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Izohli.lug.at.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "type")
public class TypeController implements SimpleRequestCrud<Integer, RequestTypeDto, TypeDto> {
    private final TypeService typeService;
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<TypeDto>> createEntity(@RequestBody @Valid RequestTypeDto entity) {
        return convertStatusCodeByData(this.typeService.createEntity(entity));
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseDto<TypeDto>> getEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.typeService.getEntity(entityId));
    }

    @Override
    @PutMapping
    public ResponseEntity<ResponseDto<TypeDto>> updateEntity(@RequestParam(value = "id")Integer entityId,
                                                             @RequestBody @Valid  RequestTypeDto entity) {
        return convertStatusCodeByData(this.typeService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ResponseDto<TypeDto>> deleteEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.typeService.deleteEntity(entityId));
    }
}
