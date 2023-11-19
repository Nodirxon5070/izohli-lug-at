package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestDayWordDto;
import com.company.Izohli.lug.at.dto.responseDto.DayWordDto;
import com.company.Izohli.lug.at.service.DayWordService;
import com.company.Izohli.lug.at.utill.SimpleRequestCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Izohli.lug.at.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "dayWord")
public class DayWordController implements SimpleRequestCrud<Integer, RequestDayWordDto, DayWordDto> {
    private final DayWordService dayWordService;
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<DayWordDto>> createEntity(@RequestBody @Valid RequestDayWordDto entity) {
        return convertStatusCodeByData(this.dayWordService.createEntity(entity));
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseDto<DayWordDto>> getEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.dayWordService.getEntity(entityId));
    }

    @Override
    @PutMapping
    public ResponseEntity<ResponseDto<DayWordDto>> updateEntity(@RequestParam(value = "id")Integer entityId,
                                                                @RequestBody @Valid  RequestDayWordDto entity) {
        return convertStatusCodeByData(this.dayWordService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ResponseDto<DayWordDto>> deleteEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.dayWordService.deleteEntity(entityId));
    }
}
