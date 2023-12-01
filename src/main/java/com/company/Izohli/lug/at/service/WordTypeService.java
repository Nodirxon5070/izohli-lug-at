package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.WordTypeDto;
import com.company.Izohli.lug.at.repository.WordTypeRepository;
import com.company.Izohli.lug.at.service.mapper.WordTypeMapper;
import com.company.Izohli.lug.at.util.SimpleCrud;
import com.company.Izohli.lug.at.validation.WordTypeValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordTypeService implements SimpleCrud<Integer, RequestWordTypeDto, WordTypeDto> {
    private final WordTypeRepository wordTypeRepository;
    private final WordTypeValidation wordTypeValidation;
    private final WordTypeMapper wordTypeMapper;
    @Lazy
    private final WordService wordService;
    @Lazy
    private final TypeService typeService;


    @Override
    public ResponseDto<WordTypeDto> createEntity(RequestWordTypeDto dto) {
        List<ErrorDto> errors;
        errors = this.wordTypeValidation.wordTypeValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<WordTypeDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .build();
        }
        try {
            return ResponseDto.<WordTypeDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.wordTypeMapper.toDto(
                            this.wordTypeRepository.save(
                                    this.wordTypeMapper.toEntity(dto)
                            )
                    ))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<WordTypeDto>builder()
                    .code(-2)
                    .message(String.format("Word while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<WordTypeDto> getEntity(Integer entityId) {
        return this.wordTypeRepository.findByWordTypeIdAndDeletedAtIsNull(entityId)
                .map(wordType -> {
                    WordTypeDto wordTypeDto = this.wordTypeMapper.toDto(wordType);
                    wordTypeDto.setType(this.typeService.getEntity(wordType.getTypeId()).getData());
                    wordTypeDto.setWord(this.wordService.getEntity(wordType.getWordId()).getData());
                    return ResponseDto.<WordTypeDto>builder()
                            .success(true)
                            .message("OK")
                            .data(wordTypeDto)
                            .build();
                })
                .orElse(ResponseDto.<WordTypeDto>builder()
                        .code(-1)
                        .message(String.format("WordType with %d id is not found", entityId))
                        .build());
    }

    @Override
    public ResponseDto<WordTypeDto> updateEntity(Integer entityId, RequestWordTypeDto dto) {
        try {
            return this.wordTypeRepository.findByWordTypeIdAndDeletedAtIsNull(entityId)
                    .map(wordType -> ResponseDto.<WordTypeDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.wordTypeMapper.toDto(
                                    this.wordTypeRepository.save(
                                            this.wordTypeMapper.updateWordType(dto, wordType)
                                    )
                            ))
                            .build())
                    .orElse(ResponseDto.<WordTypeDto>builder()
                            .code(-1)
                            .message(String.format("WordType with %d id is not found", entityId))
                            .build());

        } catch (Exception e) {
            return ResponseDto.<WordTypeDto>builder()
                    .code(-2)
                    .message(String.format("WordType while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<WordTypeDto> deleteEntity(Integer entityId) {
        return this.wordTypeRepository.findByWordTypeIdAndDeletedAtIsNull(entityId)
                .map(wordType -> {
                    wordType.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<WordTypeDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.wordTypeMapper.toDto(
                                    this.wordTypeRepository.save(wordType)
                            ))
                            .build();
                })
                .orElse(ResponseDto.<WordTypeDto>builder()
                        .code(-1)
                        .message(String.format("WordType with %d id is not found", entityId))
                        .build());
    }
}
