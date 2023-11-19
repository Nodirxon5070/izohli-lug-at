package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestNoteDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.service.NoteService;
import com.company.Izohli.lug.at.utill.SimpleRequestCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Izohli.lug.at.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "note")
public class NoteController implements SimpleRequestCrud<Integer, RequestNoteDto, NoteDto> {
    private final NoteService noteService;
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<NoteDto>> createEntity(@RequestBody @Valid RequestNoteDto entity) {
        return convertStatusCodeByData(this.noteService.createEntity(entity));
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseDto<NoteDto>> getEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.noteService.getEntity(entityId));
    }

    @Override
    @PutMapping
    public ResponseEntity<ResponseDto<NoteDto>> updateEntity(@RequestParam(value = "id")Integer entityId,
                                                             @RequestBody @Valid   RequestNoteDto entity) {
        return convertStatusCodeByData(this.noteService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ResponseDto<NoteDto>> deleteEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.noteService.deleteEntity(entityId));
    }
}
