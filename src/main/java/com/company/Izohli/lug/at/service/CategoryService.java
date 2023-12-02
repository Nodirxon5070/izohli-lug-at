package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestCategoryDto;
import com.company.Izohli.lug.at.dto.responseDto.CategoryDto;
import com.company.Izohli.lug.at.module.Category;
import com.company.Izohli.lug.at.repository.CategoryRepository;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.service.mapper.CategoryMapper;
import com.company.Izohli.lug.at.service.mapper.WordMapper;
import com.company.Izohli.lug.at.util.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements SimpleCrud<Integer, RequestCategoryDto, CategoryDto> {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseDto<CategoryDto> createEntity(RequestCategoryDto dto) {
        try {
            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.categoryMapper.toDto(
                            this.categoryRepository.save(
                                    categoryMapper.toEntity(dto))
                    ))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-2)
                    .message(String.format("Category while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<CategoryDto> getEntity(Integer entityId) {
        return this.categoryRepository.findByCategoryId(entityId)
                .map(category ->
                        ResponseDto.<CategoryDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.categoryMapper.toDtoWithWord(category))
                                .build()
                ).orElse(ResponseDto.<CategoryDto>builder()
                        .code(-1)
                        .message(String.format("Category with %d id is not found", entityId))
                        .build());

    }

    @Override
    public ResponseDto<CategoryDto> updateEntity(Integer entityId, RequestCategoryDto dto) {
     try {
            return this.categoryRepository.findByCategoryId(entityId)
                    .map(category -> ResponseDto.<CategoryDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.categoryMapper.toDto
                                    (this.categoryRepository.save(
                                            this.categoryMapper.updateCategory(dto, category))))
                            .build())
                    .orElse(ResponseDto.<CategoryDto>builder()
                            .code(-1)
                            .message("Category is not found")
                            .build());
        }catch (Exception e){
         return ResponseDto.<CategoryDto>builder()
               .code(-2)
               .message(String.format("Category while updating error %s", e.getMessage()))
               .build();
     }

    }

    @Override
    public ResponseDto<CategoryDto> deleteEntity(Integer entityId) {
       try {
            return this.categoryRepository.findByCategoryId(entityId)
                    .map(category -> {
                        this.categoryRepository.delete(category);
                        return ResponseDto.<CategoryDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.categoryMapper.toDto(category))
                                .build();
                    })
                    .orElse(ResponseDto.<CategoryDto>builder()
                            .code(-1)
                            .message(String.format("Category with %d id is not found", entityId))
                            .build());
        }catch (Exception e){
           return ResponseDto.<CategoryDto>builder()
                 .code(-2)
                 .message(String.format("Category while deleting error %s", e.getMessage()))
                 .build();
       }
    }
}
