package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.DayWordDto;
import com.company.Izohli.lug.at.dto.responseDto.SentenceDto;
import com.company.Izohli.lug.at.mapper.SentenceMapper;
import com.company.Izohli.lug.at.repository.SentenceRepository;
import com.company.Izohli.lug.at.utill.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SentenceService implements SimpleCrud<Integer, RequestSentenceDto, SentenceDto> {
    private final SentenceRepository sentenceRepository;
    private final SentenceMapper sentenceMapper;
    @Override
    public ResponseDto<SentenceDto> createEntity(RequestSentenceDto dto) {
        try {
            return ResponseDto.<SentenceDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.sentenceMapper.toDto(
                            this.sentenceRepository.save(
                                    this.sentenceMapper.toEntity(dto)
                            )
                    ))
                    .build();
        }catch (Exception e) {
            return ResponseDto.<SentenceDto>builder()
                    .code(-2)
                    .message(String.format("Sentence while saving error %s",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<SentenceDto> getEntity(Integer entityId) {
        return this.sentenceRepository.findBySentenceIdAndDeletedAtIsNull(entityId)
                .map(sentence ->  ResponseDto.<SentenceDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.sentenceMapper.toDto(sentence))
                        .build()
                ).orElse(ResponseDto.<SentenceDto>builder()
                        .code(-1)
                        .message(String.format("Sentence with %d id is not found",entityId))
                        .build());
    }

    @Override
    public ResponseDto<SentenceDto> updateEntity(Integer entityId, RequestSentenceDto entity) {
        try {
            return this.sentenceRepository.findBySentenceIdAndDeletedAtIsNull(entityId)
                    .map(sentence ->  ResponseDto.<SentenceDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.sentenceMapper.toDto(
                                    this.sentenceRepository.save(
                                            this.sentenceMapper.updateSentence(entity,sentence)
                                    )
                            ))
                            .build())
                    .orElse(ResponseDto.<SentenceDto>builder()
                            .code(-1)
                            .message(String.format("Sentence with %d id is not found",entityId))
                            .build())     ;
        }catch (Exception e) {
            return ResponseDto.<SentenceDto>builder()
                    .code(-2)
                    .message(String.format("Sentence while saving error %s",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<SentenceDto> deleteEntity(Integer entityId) {
        return this.sentenceRepository.findBySentenceIdAndDeletedAtIsNull(entityId)
                .map(sentence -> {
                    sentence.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<SentenceDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.sentenceMapper.toDto(
                                    this.sentenceRepository.save(sentence)
                            ))
                            .build();
                })
                .orElse(ResponseDto.<SentenceDto>builder()
                        .code(-1)
                        .message(String.format("Sentence with %d id is not found",entityId))
                        .build());
    }
}
