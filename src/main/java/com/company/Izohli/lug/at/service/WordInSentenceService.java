package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordInSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.mapper.WordInSentenceMapper;
import com.company.Izohli.lug.at.repository.WordInSentenceRepository;
import com.company.Izohli.lug.at.utill.SimpleCrud;
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
        List<ErrorDto>errors;
        errors = this.wordInSentenceValidation.wordInSentenceValid(dto);
        if (!errors.isEmpty()){
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
                    .build() ;
        }catch (Exception e){
            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-2)
                    .message(String.format("WordInSentence while saving error %s",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<WordInSentenceDto> getEntity(Integer entityId) {
        return this.wordInSentenceRepository.findByWordInSentenceIdAndDeletedAtIsNull(entityId)
                .map(wordInSentence ->  ResponseDto.<WordInSentenceDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.wordInSentenceMapper.toDto(wordInSentence))
                        .build())
                .orElse(ResponseDto.<WordInSentenceDto>builder()
                        .code(-1)
                        .message(String.format("WordInSentence with %d id is not found",entityId))
                        .build());
    }

    @Override
    public ResponseDto<WordInSentenceDto> updateEntity(Integer entityId, RequestWordInSentenceDto entity) {
        try {
            return this.wordInSentenceRepository.findByWordInSentenceIdAndDeletedAtIsNull(entityId)
                    .map(wordInSentence -> ResponseDto.<WordInSentenceDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.wordInSentenceMapper.toDto(
                                    this.wordInSentenceRepository.save(
                                            this.wordInSentenceMapper.updateWordInSentence(entity,wordInSentence)
                                    )
                            ))
                            .build())
                    .orElse(ResponseDto.<WordInSentenceDto>builder()
                            .code(-1)
                            .message(String.format("WordInSentenceDto with %d id is not found",entityId) )
                            .build());

        }catch (Exception e) {
            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-2)
                    .message(String.format("WordInSentence while saving error %s",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<WordInSentenceDto> deleteEntity(Integer entityId) {
        return this.wordInSentenceRepository.findByWordInSentenceIdAndDeletedAtIsNull(entityId)
                .map(wordInSentence -> {
                    wordInSentence.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<WordInSentenceDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.wordInSentenceMapper.toDto(
                                    this.wordInSentenceRepository.save(wordInSentence)
                            ))
                            .build();
                })
                .orElse(ResponseDto.<WordInSentenceDto>builder()
                        .code(-1)
                        .message(String.format("WordInSentence with %d id is not found",entityId))
                        .build());
    }
}
