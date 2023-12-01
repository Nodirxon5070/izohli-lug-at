package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.repository.*;
import com.company.Izohli.lug.at.service.mapper.*;
import com.company.Izohli.lug.at.util.SimpleCrud;
import com.company.Izohli.lug.at.util.WordRepositoryImpl;
import com.company.Izohli.lug.at.validation.WordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordService implements SimpleCrud<Integer, RequestWordDto, WordDto> {
    private final WordRepository wordRepository;
    private final WordMapper wordMapper;
    private final WordValidation wordValidation;
    private final WordRepositoryImpl wordRepositoryimpl;
    @Lazy
    private final AudioMapper audioMapper;
    @Lazy
    private final AudioRepository audioRepository;
    @Lazy
    private final CategoryRepository categoryRepository;
    @Lazy
    private final CategoryMapper categoryMapper;
    @Lazy
    private final WordTypeRepository wordTypeRepository;
    @Lazy
    private final WordTypeMapper wordTypeMapper;
    @Lazy
    private final NoteRepository noteRepository;
    @Lazy
    private final NoteMapper noteMapper;
    @Lazy
    private final WordInSentenceRepository wordInSentenceRepository;
    @Lazy
    private final WordInSentenceMapper wordInSentenceMapper;
    @Lazy
    private final DayWordMapper dayWordMapper;
    @Lazy
    private final DayWordRepository dayWordRepository;


    @Override
    public ResponseDto<WordDto> createEntity(RequestWordDto dto) {
        List<ErrorDto> errors;
        errors = this.wordValidation.wordValid(dto);
        if (!errors.isEmpty()) {
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

        } catch (Exception e) {
            return ResponseDto.<WordDto>builder()
                    .code(-2)
                    .message(String.format("Word while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<WordDto> getEntity(Integer entityId) {
        return this.wordRepository.findByWordIdAndDeletedAtIsNull(entityId)
                .map(word -> {
                    WordDto wordDto = this.wordMapper.toDto(word);
                    return ResponseDto.<WordDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(wordDto)
                            .build();
                })
                .orElse(ResponseDto.<WordDto>builder()
                        .code(-1)
                        .message(String.format("Word with %d id is not found", entityId))
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
                                            this.wordMapper.updateWord(entity, word)
                                    )
                            ))
                            .build())
                    .orElse(ResponseDto.<WordDto>builder()
                            .code(-1)
                            .message(String.format("Word with %d id is not found", entityId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<WordDto>builder()
                    .code(-2)
                    .message(String.format("Word while saving error %s", e.getMessage()))
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
                        .message(String.format("Word with %d id is not found", entityId))
                        .build());
    }

    public ResponseDto<Page<WordDto>> getAllWordAdvancedSearch(Map<String, String> params) {
        return Optional.of(this.wordRepositoryimpl.searchWordByMoreParams(params)
                        .map(this.wordMapper::toDtoWithAll))
                .map(word -> ResponseDto.<Page<WordDto>>builder()
                        .success(true)
                        .message("OK")
                        .data(word)
                        .build())
                .orElse(ResponseDto.<Page<WordDto>>builder()
                        .message("Word not found!")
                        .build());
    }


}
