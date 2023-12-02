package com.company.Izohli.lug.at.testType;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.TypeDto;
import com.company.Izohli.lug.at.module.Type;
import com.company.Izohli.lug.at.repository.TypeRepository;
import com.company.Izohli.lug.at.service.TypeService;
import com.company.Izohli.lug.at.service.mapper.TypeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestType {
    private TypeService typeService;
    private TypeMapper typeMapper;
    private TypeRepository typeRepository;


    @BeforeEach
    void initObject() {

        this.typeMapper = mock(TypeMapper.class);
        this.typeRepository = mock(TypeRepository.class);
        this.typeService = new TypeService(typeRepository, typeMapper);
    }



    @Test
    void testCreatePositive() {
        when(this.typeMapper.toEntity(any()))
                .thenReturn(Type.builder()
                        .typeId(1)
                        .name("name")
                        .build());
        when(this.typeMapper.toDto(any()))
                .thenReturn(TypeDto.builder()
                        .typeId(1)
                        .name("name")
                        .build());


        ResponseDto<TypeDto> responseDto = this.typeService.createEntity(any());

        assertEquals(responseDto.getCode(), 0, "Error responseDto code");
        assertEquals(responseDto.getData().getTypeId(), 1);
        assertTrue(responseDto.isSuccess());
        assertNotNull(responseDto.getData());
        assertNull(responseDto.getErrorList());

        verify(this.typeMapper, times(1)).toDto(any());
        verify(this.typeRepository, times(1)).save(any());

    }


    @Test
    void testCreateException() {
        when(typeMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<TypeDto> response = this.typeService.createEntity(any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());

    }

    @Test
    void testGetPositive() {
        when(this.typeMapper.toDtoWithWordType(any()))
                .thenReturn(TypeDto.builder()
                        .typeId(1)
                        .name("name")
                        .build());
        when(this.typeRepository.findByTypeId(any()))
                .thenReturn(Optional.ofNullable(Type.builder()
                        .typeId(1)
                        .name("name")
                        .build()));

        ResponseDto<TypeDto> response = this.typeService.getEntity(any());

        assertEquals(response.getCode(), 0);
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(response.getData().getTypeId(), 1);

        verify(this.typeRepository, times(1)).findByTypeId(any());
        verify(this.typeMapper, times(1)).toDtoWithWordType(any());
    }

    @Test
    void testGetNegative() {
        when(this.typeRepository.findByTypeId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<TypeDto> response = this.typeService.getEntity(any());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertFalse(response.isSuccess());


        verify(this.typeRepository, times(1)).findByTypeId(any());
    }
    @Test
    void testUpdatePositive() {
        when(typeRepository.findByTypeId(any()))
                .thenReturn(Optional.ofNullable(Type.builder()
                        .typeId(1)
                        .name("name")
                        .build()));

        when(typeMapper.updateType(any(), any()))
                .thenReturn(Type.builder()
                        .typeId(2)
                        .name("name")
                        .build());

        when(typeMapper.toDto(any()))
                .thenReturn(TypeDto.builder()
                        .typeId(3)
                        .name("name")
                        .build());

        ResponseDto<TypeDto> response = this.typeService.updateEntity(any(),
                RequestTypeDto.builder()
                        .name("name")
                        .build());

        assertTrue(response.isSuccess());
        assertEquals(response.getCode(), 0);
        assertEquals(response.getData().getTypeId(), 3);

        verify(this.typeMapper, times(1)).toDto(any());
        verify(this.typeMapper, times(1)).updateType(any(), any());
        verify(this.typeRepository, times(1)).save(any());
        verify(this.typeRepository, times(1)).findByTypeId(any());

    }

    @Test
    void testUpdateNegative() {
        when(this.typeRepository.findByTypeId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<TypeDto> response = this.typeService.updateEntity(1, any());

        assertFalse(response.isSuccess());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertEquals(response.getMessage(), "Type with 1 id is not found");

        verify(this.typeRepository, times(1)).findByTypeId(any());
    }

    @Test
    void testUpdateException() {
        when(this.typeRepository.findByTypeId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<TypeDto> response = this.typeService.updateEntity(1, any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertNull(response.getErrorList());

        verify(this.typeRepository, times(1)).findByTypeId(any());
    }

    @Test
    void testDeletePositive() {
        when(this.typeRepository.findByTypeId(any()))
                .thenReturn(Optional.of(
                        Type.builder()
                                .typeId(1)
                                .name("name")
                                .build()));
        when(this.typeMapper.toDto(any()))
                .thenReturn(TypeDto.builder()
                        .typeId(3)
                        .name("name")
                        .build());
        ResponseDto<TypeDto> response = this.typeService.deleteEntity(any());

        assertTrue(response.isSuccess());
        assertEquals(response.getMessage(), "Ok");
        assertNotNull(response.getData());
        assertEquals(response.getCode(), 0);

        verify(this.typeRepository, times(1)).findByTypeId(any());
        verify(this.typeRepository, times(1)).delete(any());
        verify(this.typeMapper, times(1)).toDto(any());

    }

    @Test
    void testDeleteNegative() {
        when(this.typeRepository.findByTypeId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<TypeDto> response = this.typeService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertEquals(response.getMessage(), "Type with null id is not found");
        assertNull(response.getData());
        assertEquals(response.getCode(), -1);

        verify(this.typeRepository, times(1)).findByTypeId(any());
    }

    @Test
    void testDeleteException() {
        when(this.typeRepository.findByTypeId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<TypeDto> response = this.typeService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals(response.getCode(), -2);

        verify(this.typeRepository, times(1)).findByTypeId(any());

    }





}
