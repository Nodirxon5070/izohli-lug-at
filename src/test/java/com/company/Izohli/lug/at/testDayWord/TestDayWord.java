package com.company.Izohli.lug.at.testDayWord;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestDayWordDto;
import com.company.Izohli.lug.at.dto.responseDto.DayWordDto;
import com.company.Izohli.lug.at.module.DayWord;
import com.company.Izohli.lug.at.repository.DayWordRepository;
import com.company.Izohli.lug.at.service.DayWordService;
import com.company.Izohli.lug.at.service.mapper.DayWordMapper;
import com.company.Izohli.lug.at.validation.DayWordValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestDayWord {

    private DayWordService dayWordService;
    private DayWordMapper dayWordMapper;
    private DayWordValidation dayWordValidation;
    private DayWordRepository dayWordRepository;


    @BeforeEach
    void initObject() {

        this.dayWordMapper = mock(DayWordMapper.class);
        this.dayWordRepository = mock(DayWordRepository.class);
        this.dayWordValidation = mock(DayWordValidation.class);
        this.dayWordService = new DayWordService(dayWordRepository,dayWordValidation, dayWordMapper);
    }

    @Test
    void testCreatePositive() {
        when(this.dayWordMapper.toEntity(any()))
                .thenReturn(DayWord.builder()
                        .dayWordId(1)
                        .wordId(1)
                        .build());
        when(this.dayWordMapper.toDto(any()))
                .thenReturn(DayWordDto.builder()
                        .dayWordId(1)
                        .wordId(1)
                        .build());


        ResponseDto<DayWordDto> responseDto = this.dayWordService.createEntity(any());

        assertEquals(responseDto.getCode(), 0, "Error responseDto code");
        assertEquals(responseDto.getData().getDayWordId(), 1);
        assertTrue(responseDto.isSuccess());
        assertNotNull(responseDto.getData());
        assertNull(responseDto.getErrorList());

        verify(this.dayWordMapper, times(1)).toDto(any());
        verify(this.dayWordRepository, times(1)).save(any());

    }

    @Test
    void testCreateException() {
        when(dayWordMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<DayWordDto> response = this.dayWordService.createEntity(any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());

    }

    @Test
    void testGetPositive() {
        when(this.dayWordMapper.toDtoWithWord(any()))
                .thenReturn(DayWordDto.builder()
                        .dayWordId(1)
                        .wordId(1)
                        .build());
        when(this.dayWordRepository.findByDayWordId(any()))
                .thenReturn(Optional.ofNullable(DayWord.builder()
                        .dayWordId(1)
                        .wordId(1)
                        .build()));

        ResponseDto<DayWordDto> response = this.dayWordService.getEntity(any());

        assertEquals(response.getCode(), 0);
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(response.getData().getDayWordId(), 1);

        verify(this.dayWordRepository, times(1)).findByDayWordId(any());
        verify(this.dayWordMapper, times(1)).toDtoWithWord(any());
    }

    @Test
    void testGetNegative() {
        when(this.dayWordRepository.findByDayWordId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<DayWordDto> response = this.dayWordService.getEntity(any());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertFalse(response.isSuccess());


        verify(this.dayWordRepository, times(1)).findByDayWordId(any());
    }

    @Test
    void testUpdatePositive() {
        when(dayWordRepository.findByDayWordId(any()))
                .thenReturn(Optional.ofNullable(DayWord.builder()
                        .dayWordId(1)
                        .wordId(1)
                        .build()));

        when(dayWordMapper.updateDayWord(any(), any()))
                .thenReturn(DayWord.builder()
                        .dayWordId(2)
                        .wordId(1)
                        .build());

        when(dayWordMapper.toDto(any()))
                .thenReturn(DayWordDto.builder()
                        .dayWordId(3)
                        .wordId(1)
                        .build());

        ResponseDto<DayWordDto> response = this.dayWordService.updateEntity(any(), RequestDayWordDto.builder()
                .wordId(1)
                .build());

        assertTrue(response.isSuccess());
        assertEquals(response.getCode(), 0);
        assertEquals(response.getData().getDayWordId(), 3);

        verify(this.dayWordMapper, times(1)).toDto(any());
        verify(this.dayWordMapper, times(1)).updateDayWord(any(), any());
        verify(this.dayWordRepository, times(1)).save(any());
        verify(this.dayWordRepository, times(1)).findByDayWordId(any());

    }

    @Test
    void testUpdateNegative() {
        when(this.dayWordRepository.findByDayWordId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<DayWordDto> response = this.dayWordService.updateEntity(1, any());

        assertFalse(response.isSuccess());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertEquals(response.getMessage(), "DayWord with 1 id is not found");

        verify(this.dayWordRepository, times(1)).findByDayWordId(any());
    }

    @Test
    void testUpdateException() {
        when(this.dayWordRepository.findByDayWordId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<DayWordDto> response = this.dayWordService.updateEntity(1, any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertNull(response.getErrorList());

        verify(this.dayWordRepository, times(1)).findByDayWordId(any());
    }


    @Test
    void testDeletePositive() {
        when(this.dayWordRepository.findByDayWordId(any()))
                .thenReturn(Optional.of(
                        DayWord.builder()
                                .dayWordId(1)
                                .wordId(1)
                                .build()));
        when(this.dayWordMapper.toDto(any()))
                .thenReturn(DayWordDto.builder()
                        .dayWordId(3)
                        .wordId(1)
                        .build());
        ResponseDto<DayWordDto> response = this.dayWordService.deleteEntity(any());

        assertTrue(response.isSuccess());
        assertEquals(response.getMessage(), "OK");
        assertNotNull(response.getData());
        assertEquals(response.getCode(), 0);

        verify(this.dayWordRepository, times(1)).findByDayWordId(any());
        verify(this.dayWordRepository, times(1)).delete(any());
        verify(this.dayWordMapper, times(1)).toDto(any());

    }

    @Test
    void testDeleteNegative() {
        when(this.dayWordRepository.findByDayWordId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<DayWordDto> response = this.dayWordService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertEquals(response.getMessage(), "DayWord with null id is not found");
        assertNull(response.getData());
        assertEquals(response.getCode(), -1);

        verify(this.dayWordRepository, times(1)).findByDayWordId(any());
    }

    @Test
    void testDeleteException() {
        when(this.dayWordRepository.findByDayWordId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<DayWordDto> response = this.dayWordService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals(response.getCode(), -2);

        verify(this.dayWordRepository, times(1)).findByDayWordId(any());

    }



}
