package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestCategoryDto;
import com.company.Izohli.lug.at.dto.responseDto.CategoryDto;
import com.company.Izohli.lug.at.service.CategoryService;
import com.company.Izohli.lug.at.utill.SimpleRequestCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Izohli.lug.at.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "category")
public class CategoryController implements SimpleRequestCrud<Integer, RequestCategoryDto, CategoryDto> {
    private final CategoryService categoryService;
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<CategoryDto>> createEntity(@RequestBody @Valid RequestCategoryDto entity) {
        return convertStatusCodeByData(this.categoryService.createEntity(entity));
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseDto<CategoryDto>> getEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.categoryService.getEntity(entityId));
    }

    @Override
    @PutMapping
    public ResponseEntity<ResponseDto<CategoryDto>> updateEntity(@RequestParam(value = "id")Integer entityId,
                                                                 @RequestBody @Valid  RequestCategoryDto entity) {
        return convertStatusCodeByData(this.categoryService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ResponseDto<CategoryDto>> deleteEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.categoryService.deleteEntity(entityId));
    }
}
