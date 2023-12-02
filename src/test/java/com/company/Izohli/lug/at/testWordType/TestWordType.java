package com.company.Izohli.lug.at.testWordType;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordTypeDto;
import com.company.Izohli.lug.at.dto.responseDto.WordTypeDto;
import com.company.Izohli.lug.at.module.WordType;
import com.company.Izohli.lug.at.repository.WordTypeRepository;
import com.company.Izohli.lug.at.service.WordTypeService;
import com.company.Izohli.lug.at.service.mapper.WordTypeMapper;
import com.company.Izohli.lug.at.validation.WordTypeValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestWordType {

    private WordTypeService wordTypeService;
    private WordTypeMapper wordTypeMapper;
    private WordTypeValidation wordTypeValidation;
    private WordTypeRepository wordTypeRepository;


    @BeforeEach
    void initObject() {

        this.wordTypeMapper = mock(WordTypeMapper.class);
        this.wordTypeValidation = mock(WordTypeValidation.class);
        this.wordTypeRepository = mock(WordTypeRepository.class);
        this.wordTypeService = new WordTypeService(wordTypeRepository,wordTypeValidation, wordTypeMapper);
    }



    @Test
    void testCreatePositive() {
        when(this.wordTypeMapper.toEntity(any()))
                .thenReturn(WordType.builder()
                        .wordTypeId(1)
                        .wordId(1)
                        .build());
        when(this.wordTypeMapper.toDto(any()))
                .thenReturn(WordTypeDto.builder()
                        .wordTypeId(1)
                        .wordId(1)
                        .build());


        ResponseDto<WordTypeDto> responseDto = this.wordTypeService.createEntity(any());

        assertEquals(responseDto.getCode(), 0, "Error responseDto code");
        assertEquals(responseDto.getData().getWordTypeId(), 1);
        assertTrue(responseDto.isSuccess());
        assertNotNull(responseDto.getData());
        assertNull(responseDto.getErrorList());

        verify(this.wordTypeMapper, times(1)).toDto(any());
        verify(this.wordTypeRepository, times(1)).save(any());

    }


    @Test
    void testCreateException() {
        when(wordTypeMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordTypeDto> response = this.wordTypeService.createEntity(any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());

    }

    @Test
    void testGetPositive() {
        when(this.wordTypeMapper.toDtoWithWordAndType(any()))
                .thenReturn(WordTypeDto.builder()
                        .wordTypeId(1)
                        .wordId(1)
                        .build());
        when(this.wordTypeRepository.findByWordTypeId(any()))
                .thenReturn(Optional.ofNullable(WordType.builder()
                        .wordTypeId(1)
                        .wordId(1)
                        .build()));

        ResponseDto<WordTypeDto> response = this.wordTypeService.getEntity(any());

        assertEquals(response.getCode(), 0);
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(response.getData().getWordTypeId(), 1);

        verify(this.wordTypeRepository, times(1)).findByWordTypeId(any());
        verify(this.wordTypeMapper, times(1)).toDtoWithWordAndType(any());
    }

    @Test
    void testGetNegative() {
        when(this.wordTypeRepository.findByWordTypeId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordTypeDto> response = this.wordTypeService.getEntity(any());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertFalse(response.isSuccess());


        verify(this.wordTypeRepository, times(1)).findByWordTypeId(any());
    }
    @Test
    void testUpdatePositive() {
        when(wordTypeRepository.findByWordTypeId(any()))
                .thenReturn(Optional.ofNullable(WordType.builder()
                        .wordTypeId(1)
                        .wordId(1)
                        .build()));

        when(wordTypeMapper.updateWordType(any(), any()))
                .thenReturn(WordType.builder()
                        .wordTypeId(2)
                        .wordId(1)
                        .build());

        when(wordTypeMapper.toDto(any()))
                .thenReturn(WordTypeDto.builder()
                        .wordTypeId(3)
                        .wordId(1)
                        .build());

        ResponseDto<WordTypeDto> response = this.wordTypeService.updateEntity(any(),
                RequestWordTypeDto.builder()
                        .wordId(1)
                        .build());

        assertTrue(response.isSuccess());
        assertEquals(response.getCode(), 0);
        assertEquals(response.getData().getWordTypeId(), 3);

        verify(this.wordTypeMapper, times(1)).toDto(any());
        verify(this.wordTypeMapper, times(1)).updateWordType(any(), any());
        verify(this.wordTypeRepository, times(1)).save(any());
        verify(this.wordTypeRepository, times(1)).findByWordTypeId(any());

    }

    @Test
    void testUpdateNegative() {
        when(this.wordTypeRepository.findByWordTypeId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordTypeDto> response = this.wordTypeService.updateEntity(1, any());

        assertFalse(response.isSuccess());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertEquals(response.getMessage(), "WordType with 1 id is not found");

        verify(this.wordTypeRepository, times(1)).findByWordTypeId(any());
    }

    @Test
    void testUpdateException() {
        when(this.wordTypeRepository.findByWordTypeId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordTypeDto> response = this.wordTypeService.updateEntity(1, any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertNull(response.getErrorList());

        verify(this.wordTypeRepository, times(1)).findByWordTypeId(any());
    }

    @Test
    void testDeletePositive() {
        when(this.wordTypeRepository.findByWordTypeId(any()))
                .thenReturn(Optional.of(
                        WordType.builder()
                                .wordTypeId(1)
                                .wordId(1)
                                .build()));
        when(this.wordTypeMapper.toDto(any()))
                .thenReturn(WordTypeDto.builder()
                        .wordTypeId(3)
                        .wordId(1)
                        .build());
        ResponseDto<WordTypeDto> response = this.wordTypeService.deleteEntity(any());

        assertTrue(response.isSuccess());
        assertEquals(response.getMessage(), "Ok");
        assertNotNull(response.getData());
        assertEquals(response.getCode(), 0);

        verify(this.wordTypeRepository, times(1)).findByWordTypeId(any());
        verify(this.wordTypeRepository, times(1)).delete(any());
        verify(this.wordTypeMapper, times(1)).toDto(any());

    }

    @Test
    void testDeleteNegative() {
        when(this.wordTypeRepository.findByWordTypeId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordTypeDto> response = this.wordTypeService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertEquals(response.getMessage(), "WordType with null id is not found");
        assertNull(response.getData());
        assertEquals(response.getCode(), -1);

        verify(this.wordTypeRepository, times(1)).findByWordTypeId(any());
    }

    @Test
    void testDeleteException() {
        when(this.wordTypeRepository.findByWordTypeId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordTypeDto> response = this.wordTypeService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals(response.getCode(), -2);

        verify(this.wordTypeRepository, times(1)).findByWordTypeId(any());

    }



}
