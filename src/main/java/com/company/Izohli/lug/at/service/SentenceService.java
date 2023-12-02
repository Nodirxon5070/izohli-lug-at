package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.SentenceDto;
import com.company.Izohli.lug.at.repository.SentenceRepository;
import com.company.Izohli.lug.at.repository.WordInSentenceRepository;
import com.company.Izohli.lug.at.service.mapper.SentenceMapper;
import com.company.Izohli.lug.at.service.mapper.WordInSentenceMapper;
import com.company.Izohli.lug.at.util.SimpleCrud;
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
        return this.sentenceRepository.findBySentenceId(entityId)
                .map(sentence -> ResponseDto.<SentenceDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.sentenceMapper.toDtoWithWordInSentence(sentence))
                        .build())
                .orElse(ResponseDto.<SentenceDto>builder()
                        .code(-1)
                        .message(String.format("Sentence with %d id is not found",entityId))
                        .build());
    }

    @Override
    public ResponseDto<SentenceDto> updateEntity(Integer entityId, RequestSentenceDto entity) {
        try {
            return this.sentenceRepository.findBySentenceId(entityId)
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
                    .message(String.format("Sentence while updating error %s",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<SentenceDto> deleteEntity(Integer entityId) {
        try {
            return this.sentenceRepository.findBySentenceId(entityId)
                    .map(sentence -> {
                        this.sentenceRepository.delete(sentence);
                        return ResponseDto.<SentenceDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.sentenceMapper.toDto(sentence))
                                .build();
                    })
                    .orElse(ResponseDto.<SentenceDto>builder()
                            .code(-1)
                            .message(String.format("Sentence with %d id is not found", entityId))
                            .build());
        }catch (Exception e) {
            return ResponseDto.<SentenceDto>builder()
                    .code(-2)
                    .message(String.format("Sentence while deleting error %s",e.getMessage()))
                    .build();
        }
    }
}
