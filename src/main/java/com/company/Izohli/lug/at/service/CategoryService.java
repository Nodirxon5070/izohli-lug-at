package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestCategoryDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.CategoryDto;
import com.company.Izohli.lug.at.utill.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements SimpleCrud<Integer, RequestCategoryDto, CategoryDto> {
    @Override
    public ResponseDto<CategoryDto> createEntity(RequestCategoryDto entity) {
        return null;
    }

    @Override
    public ResponseDto<CategoryDto> getEntity(Integer entityId) {
        return null;
    }

    @Override
    public ResponseDto<CategoryDto> updateEntity(Integer entityId, RequestCategoryDto entity) {
        return null;
    }

    @Override
    public ResponseDto<CategoryDto> deleteEntity(Integer entityId) {
        return null;
    }
}
