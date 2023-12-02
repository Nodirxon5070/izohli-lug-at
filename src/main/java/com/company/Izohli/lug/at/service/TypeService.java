package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.TypeDto;
import com.company.Izohli.lug.at.repository.TypeRepository;
import com.company.Izohli.lug.at.repository.WordTypeRepository;
import com.company.Izohli.lug.at.service.mapper.TypeMapper;
import com.company.Izohli.lug.at.service.mapper.WordTypeMapper;
import com.company.Izohli.lug.at.util.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TypeService implements SimpleCrud<Integer, RequestTypeDto, TypeDto> {
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;


    @Override
    public ResponseDto<TypeDto> createEntity(RequestTypeDto dto) {
        try {
            return ResponseDto.<TypeDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.typeMapper.toDto(
                            this.typeRepository.save(
                                    this.typeMapper.toEntity(dto)
                            )
                    ))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<TypeDto>builder()
                    .code(-2)
                    .message(String.format("Type while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<TypeDto> getEntity(Integer entityId) {
        return this.typeRepository.findByTypeId(entityId)
                .map(type -> ResponseDto.<TypeDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.typeMapper.toDtoWithWordType(type))
                            .build())
                .orElse(ResponseDto.<TypeDto>builder()
                        .code(-1)
                        .message(String.format("Type with %d id is not found", entityId))
                        .build());
    }

    @Override
    public ResponseDto<TypeDto> updateEntity(Integer entityId, RequestTypeDto entity) {
        try {
            return this.typeRepository.findByTypeId(entityId)
                    .map(type -> ResponseDto.<TypeDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.typeMapper.toDto(
                                    this.typeRepository.save(
                                            this.typeMapper.updateType(entity, type)
                                    )
                            ))
                            .build())
                    .orElse(ResponseDto.<TypeDto>builder()
                            .code(-1)
                            .message(String.format("Type with %d id is not found", entityId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<TypeDto>builder()
                    .code(-2)
                    .message(String.format("Type while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<TypeDto> deleteEntity(Integer entityId) {
        return this.typeRepository.findByTypeId(entityId)
                .map(type -> {
                    this.typeRepository.delete(type);
                    return ResponseDto.<TypeDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.typeMapper.toDto(type))
                            .build();
                })
                .orElse(ResponseDto.<TypeDto>builder()
                        .code(-1)
                        .message(String.format("Type with %d id is not found", entityId))
                        .build());
    }
}
