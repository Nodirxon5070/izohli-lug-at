package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordInSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.service.WordInSentenceService;
import com.company.Izohli.lug.at.utill.SimpleRequestCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Izohli.lug.at.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "wordInSentece")
public class WordInSentenceController implements SimpleRequestCrud<Integer, RequestWordInSentenceDto, WordInSentenceDto> {
    private final WordInSentenceService wordInSentenceService;
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<WordInSentenceDto>> createEntity(@RequestBody @Valid RequestWordInSentenceDto entity) {
        return convertStatusCodeByData(this.wordInSentenceService.createEntity(entity));
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseDto<WordInSentenceDto>> getEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.wordInSentenceService.getEntity(entityId));
    }

    @Override
    @PutMapping
    public ResponseEntity<ResponseDto<WordInSentenceDto>> updateEntity(@RequestParam(value = "id")Integer entityId,
                                                                       @RequestBody @Valid   RequestWordInSentenceDto entity) {
        return convertStatusCodeByData(this.wordInSentenceService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ResponseDto<WordInSentenceDto>> deleteEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.wordInSentenceService.deleteEntity(entityId));
    }
}
