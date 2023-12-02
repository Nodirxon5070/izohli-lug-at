package com.company.Izohli.lug.at.testCategory;


import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestCategoryDto;
import com.company.Izohli.lug.at.dto.responseDto.CategoryDto;
import com.company.Izohli.lug.at.module.Category;
import com.company.Izohli.lug.at.repository.CategoryRepository;
import com.company.Izohli.lug.at.service.CategoryService;
import com.company.Izohli.lug.at.service.mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestCategory {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;


    @BeforeEach
    void initObject() {

        this.categoryMapper = mock(CategoryMapper.class);
        this.categoryRepository = mock(CategoryRepository.class);
        this.categoryService = new CategoryService(categoryRepository, categoryMapper);
    }

    @Test
    void testCreatePositive() {
        when(this.categoryMapper.toEntity(any()))
                .thenReturn(Category.builder()
                        .categoryId(1)
                        .name("Category1")
                        .build());
        when(this.categoryMapper.toDto(any()))
                .thenReturn(CategoryDto.builder()
                        .categoryId(1)
                        .name("Category1")
                        .build());


        ResponseDto<CategoryDto> responseDto = this.categoryService.createEntity(any());

        assertEquals(responseDto.getCode(), 0, "Error responseDto code");
        assertEquals(responseDto.getData().getName(), "Category1");
        assertTrue(responseDto.isSuccess());
        assertNotNull(responseDto.getData());
        assertNull(responseDto.getErrorList());

        verify(this.categoryMapper, times(1)).toDto(any());
        verify(this.categoryRepository, times(1)).save(any());


    }

    @Test
    void testCreateException() {
        when(categoryMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<CategoryDto> response = this.categoryService.createEntity(any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());

    }

    @Test
    void testGetPositive() {
        when(this.categoryMapper.toDtoWithWord(any()))
                .thenReturn(CategoryDto.builder()
                        .categoryId(1)
                        .name("Category1")
                        .description("Category")
                        .build());
        when(this.categoryRepository.findByCategoryId(any()))
                .thenReturn(Optional.ofNullable(Category.builder()
                        .categoryId(1)
                        .name("Category1")
                        .description("Category")
                        .build()));

        ResponseDto<CategoryDto> response = this.categoryService.getEntity(any());

        assertEquals(response.getCode(), 0);
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(response.getData().getCategoryId(), 1);

        verify(this.categoryRepository, times(1)).findByCategoryId(any());
        verify(this.categoryMapper, times(1)).toDtoWithWord(any());
    }

    @Test
    void testGetNegative() {
        when(this.categoryRepository.findByCategoryId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<CategoryDto> response = this.categoryService.getEntity(any());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertFalse(response.isSuccess());


        verify(this.categoryRepository, times(1)).findByCategoryId(any());
    }

    @Test
    void testUpdatePositive() {
        when(categoryRepository.findByCategoryId(any()))
                .thenReturn(Optional.ofNullable(Category.builder()
                        .categoryId(1)
                        .name("Category1")
                        .description("Category")
                        .build()));

        when(categoryMapper.updateCategory(any(), any()))
                .thenReturn(Category.builder()
                        .categoryId(2)
                        .name("Category1")
                        .description("Category")
                        .build());
        when(categoryMapper.toDto(any()))
                .thenReturn(CategoryDto.builder()
                        .categoryId(3)
                        .name("Category1")
                        .description("Category")
                        .build());

        ResponseDto<CategoryDto> response = this.categoryService.updateEntity(any(), RequestCategoryDto.builder()
                .name("Category2")
                .description("Category2")
                .build());

        assertTrue(response.isSuccess());
        assertEquals(response.getCode(), 0);
        assertEquals(response.getData().getCategoryId(), 3);

        verify(this.categoryMapper, times(1)).toDto(any());
        verify(this.categoryMapper, times(1)).updateCategory(any(), any());
        verify(this.categoryRepository, times(1)).save(any());
        verify(this.categoryRepository, times(1)).findByCategoryId(any());

    }

    @Test
    void testUpdateNegative() {
        when(this.categoryRepository.findByCategoryId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<CategoryDto> response = this.categoryService.updateEntity(1, any());

        assertFalse(response.isSuccess());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertEquals(response.getMessage(), "Category is not found");

        verify(this.categoryRepository, times(1)).findByCategoryId(any());
    }

    @Test
    void testUpdateException() {
        when(this.categoryRepository.findByCategoryId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<CategoryDto> response = this.categoryService.updateEntity(1, any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertNull(response.getErrorList());

        verify(this.categoryRepository, times(1)).findByCategoryId(any());
    }

    @Test
    void testDeletePositive() {
        when(this.categoryRepository.findByCategoryId(any()))
                .thenReturn(Optional.of(
                        Category.builder()
                                .categoryId(2)
                                .name("Category1")
                                .description("Category")
                                .build()));
        when(this.categoryMapper.toDto(any()))
                .thenReturn(CategoryDto.builder()
                        .categoryId(1)
                        .name("Category1")
                        .description("Category")
                        .build());
        ResponseDto<CategoryDto> response = this.categoryService.deleteEntity(any());

        assertTrue(response.isSuccess());
        assertEquals(response.getMessage(), "Ok");
        assertNotNull(response.getData());
        assertEquals(response.getCode(), 0);

        verify(this.categoryRepository, times(1)).findByCategoryId(any());
        verify(this.categoryRepository, times(1)).delete(any());
        verify(this.categoryMapper, times(1)).toDto(any());

    }

    @Test
    void testDeleteNegative() {
        when(this.categoryRepository.findByCategoryId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<CategoryDto> response = this.categoryService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertEquals(response.getMessage(), "Category with null id is not found");
        assertNull(response.getData());
        assertEquals(response.getCode(), -1);

        verify(this.categoryRepository, times(1)).findByCategoryId(any());
    }

    @Test
    void testDeleteException() {
        when(this.categoryRepository.findByCategoryId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<CategoryDto> response = this.categoryService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals(response.getCode(), -2);

        verify(this.categoryRepository, times(1)).findByCategoryId(any());

    }


}
