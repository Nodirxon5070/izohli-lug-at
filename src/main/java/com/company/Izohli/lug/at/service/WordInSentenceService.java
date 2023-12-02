package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordInSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.repository.SentenceRepository;
import com.company.Izohli.lug.at.repository.WordInSentenceRepository;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.service.mapper.SentenceMapper;
import com.company.Izohli.lug.at.service.mapper.WordInSentenceMapper;
import com.company.Izohli.lug.at.service.mapper.WordMapper;
import com.company.Izohli.lug.at.util.SimpleCrud;
import com.company.Izohli.lug.at.validation.WordInSentenceValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordInSentenceService implements SimpleCrud<Integer, RequestWordInSentenceDto, WordInSentenceDto> {
    private final WordInSentenceRepository wordInSentenceRepository;
    private final WordInSentenceMapper wordInSentenceMapper;
    private final WordInSentenceValidation wordInSentenceValidation;

    @Override
    public ResponseDto<WordInSentenceDto> createEntity(RequestWordInSentenceDto dto) {
        List<ErrorDto> errors= this.wordInSentenceValidation.wordInSentenceValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }
        try {
            return ResponseDto.<WordInSentenceDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.wordInSentenceMapper.toDto(
                            this.wordInSentenceRepository.save(
                                    this.wordInSentenceMapper.toEntity(dto)
                            )
                    ))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-2)
                    .message(String.format("WordInSentence while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<WordInSentenceDto> getEntity(Integer entityId) {
        return this.wordInSentenceRepository.findByWordInSentenceId(entityId)
                .map(wordInSentence -> ResponseDto.<WordInSentenceDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.wordInSentenceMapper.toDtoWithWordsAndSentence(wordInSentence))
                            .build())
                .orElse(ResponseDto.<WordInSentenceDto>builder()
                        .code(-1)
                        .message(String.format("WordInSentence with %d id is not found", entityId))
                        .build());
    }

    @Override
    public ResponseDto<WordInSentenceDto> updateEntity(Integer entityId, RequestWordInSentenceDto dto) {
        List<ErrorDto> errors= this.wordInSentenceValidation.wordInSentenceValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }
        try {
            return this.wordInSentenceRepository.findByWordInSentenceId(entityId)
                    .map(wordInSentence -> ResponseDto.<WordInSentenceDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.wordInSentenceMapper.toDto(
                                    this.wordInSentenceRepository.save(
                                            this.wordInSentenceMapper.updateWordInSentence(dto, wordInSentence)
                                    )
                            ))
                            .build())
                    .orElse(ResponseDto.<WordInSentenceDto>builder()
                            .code(-1)
                            .message(String.format("WordInSentence with %d id is not found", entityId))
                            .build());

        } catch (Exception e) {
            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-2)
                    .message(String.format("WordInSentence while updating error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<WordInSentenceDto> deleteEntity(Integer entityId) {
        try {
            return this.wordInSentenceRepository.findByWordInSentenceId(entityId)
                    .map(wordInSentence -> {
                        this.wordInSentenceRepository.delete(wordInSentence);
                        return ResponseDto.<WordInSentenceDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.wordInSentenceMapper.toDto(wordInSentence))
                                .build();
                    })
                    .orElse(ResponseDto.<WordInSentenceDto>builder()
                            .code(-1)
                            .message(String.format("WordInSentence with %d id is not found", entityId))
                            .build());
        }
        catch (Exception e) {
            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-2)
                    .message(String.format("WordInSentence while deleting error %s", e.getMessage()))
                    .build();
        }
    }
}
