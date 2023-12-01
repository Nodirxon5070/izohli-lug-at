package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestDayWordDto;
import com.company.Izohli.lug.at.dto.responseDto.DayWordDto;
import com.company.Izohli.lug.at.module.DayWord;
import com.company.Izohli.lug.at.repository.DayWordRepository;
import com.company.Izohli.lug.at.service.mapper.DayWordMapper;
import com.company.Izohli.lug.at.util.SimpleCrud;
import com.company.Izohli.lug.at.validation.DayWordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DayWordService implements SimpleCrud<Integer, RequestDayWordDto, DayWordDto> {
    private final DayWordRepository dayWordRepository;
    private final DayWordValidation dayWordValidation;
    private final DayWordMapper dayWordMapper;
    private final WordService wordService;


    @Override
    public ResponseDto<DayWordDto> createEntity(RequestDayWordDto dto) {
        DayWord dayWord = this.dayWordMapper.toEntity(dto);
        dayWord.setCreatedAt(LocalDateTime.now());
        List<ErrorDto> errors;
        errors = this.dayWordValidation.dayWordValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<DayWordDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }
        try {
            return ResponseDto.<DayWordDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.dayWordMapper.toDtoWithWord(
                            this.dayWordRepository.save(dayWord)
                    ))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<DayWordDto>builder()
                    .code(-2)
                    .message(String.format("DayWord while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<DayWordDto> getEntity(Integer entityId) {
        return this.dayWordRepository.findByDayWordIdAndDeletedAtIsNull(entityId)
                .map(dayWord -> {
                    DayWordDto dto = this.dayWordMapper.toDtoWithWord(dayWord);
                    dto.setWord(wordService.getEntity(dayWord.getWordId()).getData());
                    return ResponseDto.<DayWordDto>builder()
                            .success(true)
                            .message("OK")
                            .data(dto)
                            .build();
                })
                .orElse(ResponseDto.<DayWordDto>builder()
                        .code(-1)
                        .message(String.format("DayWord with %d id is not found", entityId))
                        .build());
    }

    @Override
    public ResponseDto<DayWordDto> updateEntity(Integer entityId, RequestDayWordDto entity) {
        try {
            return this.dayWordRepository.findByDayWordIdAndDeletedAtIsNull(entityId)
                    .map(dayWord -> ResponseDto.<DayWordDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.dayWordMapper.toDto(
                                    this.dayWordRepository.save(
                                            this.dayWordMapper.updateDayWord(entity, dayWord)
                                    )
                            ))
                            .build())
                    .orElse(ResponseDto.<DayWordDto>builder()
                            .code(-1)
                            .message(String.format("DayWord with %d id is not found", entityId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<DayWordDto>builder()
                    .code(-2)
                    .message(String.format("DayWord while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<DayWordDto> deleteEntity(Integer entityId) {
        return this.dayWordRepository.findByDayWordIdAndDeletedAtIsNull(entityId)
                .map(dayWord -> {
                    dayWord.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<DayWordDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.dayWordMapper.toDto(
                                    this.dayWordRepository.save(dayWord)
                            ))
                            .build();
                }).orElse(ResponseDto.<DayWordDto>builder()
                        .code(-1)
                        .message(String.format("DayWord with %d id is not found", entityId))
                        .build());
    }
}
