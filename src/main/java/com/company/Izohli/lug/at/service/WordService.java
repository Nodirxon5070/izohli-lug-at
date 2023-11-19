package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.mapper.WordMapper;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.utill.SimpleCrud;
import com.company.Izohli.lug.at.validation.WordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService implements SimpleCrud<Integer, RequestWordDto, WordDto> {
    private final WordRepository wordRepository;
    private final WordMapper wordMapper;
    private final WordValidation wordValidation;
    @Override
    public ResponseDto<WordDto> createEntity(RequestWordDto dto) {
        List<ErrorDto>errors;
        errors=this.wordValidation.wordValid(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<WordDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }
        try {
            return ResponseDto.<WordDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.wordMapper.toDto(
                            this.wordRepository.save(
                                    this.wordMapper.toEntity(dto)
                            )
                    ))
                    .build();

        }catch (Exception e) {
            return ResponseDto.<WordDto>builder()
                    .code(-2)
                    .message(String.format("Word while saving error %s",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<WordDto> getEntity(Integer entityId) {
        return this.wordRepository.findByWordIdAndDeletedAtIsNull(entityId)
                .map(word ->ResponseDto.<WordDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.wordMapper.toDto(word))
                        .build())
                .orElse(ResponseDto.<WordDto>builder()
                        .code(-1)
                        .message(String.format("Word with %d id is not found",entityId))
                        .build());
    }

    @Override
    public ResponseDto<WordDto> updateEntity(Integer entityId, RequestWordDto entity) {
        try {
            return this.wordRepository.findByWordIdAndDeletedAtIsNull(entityId)
                    .map(word -> ResponseDto.<WordDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.wordMapper.toDto(
                                    this.wordRepository.save(
                                            this.wordMapper.updateWord(entity,word)
                                    )
                            ))
                            .build())
                    .orElse(ResponseDto.<WordDto>builder()
                            .code(-1)
                            .message(String.format("Word with %d id is not found",entityId))
                            .build());
        }catch (Exception e){
            return ResponseDto.<WordDto>builder()
                    .code(-2)
                    .message(String.format("Word while saving error %s",e.getMessage()))
                    .build();

        }
    }

    @Override
    public ResponseDto<WordDto> deleteEntity(Integer entityId) {
        return this.wordRepository.findByWordIdAndDeletedAtIsNull(entityId)
                .map(word -> {
                    word.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<WordDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.wordMapper.toDto(
                                    this.wordRepository.save(word)
                            ))
                            .build();
                })
                .orElse(ResponseDto.<WordDto>builder()
                        .code(-1)
                        .message(String.format("Word with %d id is not found",entityId))
                        .build());
    }
}
