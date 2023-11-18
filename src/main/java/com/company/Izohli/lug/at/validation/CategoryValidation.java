package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.CategoryDto;
import com.company.Izohli.lug.at.module.Category;
import com.company.Izohli.lug.at.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryValidation {
    private final CategoryRepository categoryRepository;

    public List<ErrorDto> categoryValid(CategoryDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(dto.getCategoryId()).isEmpty()) {
            errorList.add(new ErrorDto("categoryId", String.format("categoryId with %d:id is not found!", dto.getCategoryId())));
        }
        return errorList;
    }
}
