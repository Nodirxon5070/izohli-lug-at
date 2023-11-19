package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestCategoryDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.CategoryDto;
import com.company.Izohli.lug.at.mapper.CategoryMapper;
import com.company.Izohli.lug.at.module.Category;
import com.company.Izohli.lug.at.repository.CategoryRepository;
import com.company.Izohli.lug.at.utill.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements SimpleCrud<Integer, RequestCategoryDto, CategoryDto> {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseDto<CategoryDto> createEntity(RequestCategoryDto dto) {
        try {
            Category entity = categoryMapper.toEntity(dto);
            entity.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.categoryMapper.toDto(
                            this.categoryRepository.save(entity)
                    ))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-2)
                    .message(String.format("Category while saving error",e.getMessage()))
                    .build();
        }
    }
    @Override
    public ResponseDto<CategoryDto> getEntity(Integer entityId) {
        Optional<Category>optional=this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<CategoryDto>builder()
                    .code(-1)
                    .message(String.format("Category with %d id is not found",entityId))
                    .build();
        }
        return ResponseDto.<CategoryDto>builder()
                .success(true)
                .message("Ok")
                .data(this.categoryMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<CategoryDto> updateEntity(Integer entityId, RequestCategoryDto entity) {
        Optional<Category>optional=this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<CategoryDto>builder()
                    .code(-1)
                    .message("OK")
                    .data(this.categoryMapper.toDto(
                            this.categoryRepository.save(
                                    this.categoryMapper.updateCategory(entity,optional.get())
                            )
                    ))
                    .build();
        }
        Category category=this.categoryMapper.updateCategory(entity,optional.get());
        this.categoryRepository.save(this.categoryMapper.updateCategory(entity,optional.get()));
        return ResponseDto.<CategoryDto>builder()
                .success(true)
                .message("Ok")
                .data(this.categoryMapper.toDto(this.categoryRepository.save(this.categoryMapper.updateCategory(entity,optional.get()))))
                .build();
    }

    @Override
    public ResponseDto<CategoryDto> deleteEntity(Integer entityId) {
        Optional<Category>optional=this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<CategoryDto>builder()
                    .code(-1)
                    .message(String.format("Category with %d id is not found",entityId))
                    .build();
        }
        Category entity=optional.get();
        entity.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<CategoryDto>builder()
                .success(true)
                .message("Ok")
                .data(this.categoryMapper.toDto(this.categoryRepository.save(entity)))
                .build();
    }
}
