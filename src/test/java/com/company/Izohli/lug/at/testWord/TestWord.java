package com.company.Izohli.lug.at.testWord;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.module.Word;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.service.WordService;
import com.company.Izohli.lug.at.service.mapper.WordMapper;
import com.company.Izohli.lug.at.util.WordRepositoryImpl;
import com.company.Izohli.lug.at.validation.WordValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestWord {
    private WordService wordService;
    private WordValidation wordValidation;
    private WordRepositoryImpl wordRepositoryImpl;
    private WordMapper wordMapper;
    private WordRepository wordRepository;


    @BeforeEach
    void initObject() {

        this.wordMapper = mock(WordMapper.class);
        this.wordValidation=mock(WordValidation.class);
        this.wordRepositoryImpl = mock(WordRepositoryImpl.class);
        this.wordRepository = mock(WordRepository.class);
        this.wordService = new WordService(wordRepository, wordMapper,wordValidation,wordRepositoryImpl);
    }



    @Test
    void testCreatePositive() {
        when(this.wordMapper.toEntity(any()))
                .thenReturn(Word.builder()
                        .wordId(1)
                        .label("label")
                        .build());
        when(this.wordMapper.toDto(any()))
                .thenReturn(WordDto.builder()
                        .wordId(1)
                        .label("label")
                        .build());


        ResponseDto<WordDto> responseDto = this.wordService.createEntity(any());

        assertEquals(responseDto.getCode(), 0, "Error responseDto code");
        assertEquals(responseDto.getData().getWordId(), 1);
        assertTrue(responseDto.isSuccess());
        assertNotNull(responseDto.getData());
        assertNull(responseDto.getErrorList());

        verify(this.wordMapper, times(1)).toDto(any());
        verify(this.wordRepository, times(1)).save(any());

    }


    @Test
    void testCreateException() {
        when(wordMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordDto> response = this.wordService.createEntity(any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());

    }

    @Test
    void testGetPositive() {
        when(this.wordMapper.toDtoWithAll(any()))
                .thenReturn(WordDto.builder()
                        .wordId(1)
                        .label("label")
                        .build());
        when(this.wordRepository.findWordByWordId(any()))
                .thenReturn(Optional.ofNullable(Word.builder()
                        .wordId(1)
                        .label("label")
                        .build()));

        ResponseDto<WordDto> response = this.wordService.getEntity(any());

        assertEquals(response.getCode(), 0);
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(response.getData().getWordId(), 1);

        verify(this.wordRepository, times(1)).findWordByWordId(any());
        verify(this.wordMapper, times(1)).toDtoWithAll(any());
    }

    @Test
    void testGetNegative() {
        when(this.wordRepository.findWordByWordId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordDto> response = this.wordService.getEntity(any());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertFalse(response.isSuccess());


        verify(this.wordRepository, times(1)).findWordByWordId(any());
    }
    @Test
    void testUpdatePositive() {
        when(wordRepository.findWordByWordId(any()))
                .thenReturn(Optional.ofNullable(Word.builder()
                        .wordId(1)
                        .label("label")
                        .build()));

        when(wordMapper.updateWord(any(), any()))
                .thenReturn(Word.builder()
                        .wordId(2)
                        .label("label")
                        .build());

        when(wordMapper.toDto(any()))
                .thenReturn(WordDto.builder()
                        .wordId(3)
                        .label("label")
                        .build());

        ResponseDto<WordDto> response = this.wordService.updateEntity(any(),
                RequestWordDto.builder()
                        .label("label")
                        .build());

        assertTrue(response.isSuccess());
        assertEquals(response.getCode(), 0);
        assertEquals(response.getData().getWordId(), 3);

        verify(this.wordMapper, times(1)).toDto(any());
        verify(this.wordMapper, times(1)).updateWord(any(), any());
        verify(this.wordRepository, times(1)).save(any());
        verify(this.wordRepository, times(1)).findWordByWordId(any());

    }

    @Test
    void testUpdateNegative() {
        when(this.wordRepository.findWordByWordId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordDto> response = this.wordService.updateEntity(1, any());

        assertFalse(response.isSuccess());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertEquals(response.getMessage(), "Word with 1 id is not found");

        verify(this.wordRepository, times(1)).findWordByWordId(any());
    }

    @Test
    void testUpdateException() {
        when(this.wordRepository.findWordByWordId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordDto> response = this.wordService.updateEntity(1, any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertNull(response.getErrorList());

        verify(this.wordRepository, times(1)).findWordByWordId(any());
    }

    @Test
    void testDeletePositive() {
        when(this.wordRepository.findWordByWordId(any()))
                .thenReturn(Optional.of(
                        Word.builder()
                                .wordId(1)
                                .label("label")
                                .build()));
        when(this.wordMapper.toDto(any()))
                .thenReturn(WordDto.builder()
                        .wordId(3)
                        .label("label")
                        .build());
        ResponseDto<WordDto> response = this.wordService.deleteEntity(any());

        assertTrue(response.isSuccess());
        assertEquals(response.getMessage(), "Ok");
        assertNotNull(response.getData());
        assertEquals(response.getCode(), 0);

        verify(this.wordRepository, times(1)).findWordByWordId(any());
        verify(this.wordRepository, times(1)).delete(any());
        verify(this.wordMapper, times(1)).toDto(any());

    }

    @Test
    void testDeleteNegative() {
        when(this.wordRepository.findWordByWordId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordDto> response = this.wordService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertEquals(response.getMessage(), "Word with null id is not found");
        assertNull(response.getData());
        assertEquals(response.getCode(), -1);

        verify(this.wordRepository, times(1)).findWordByWordId(any());
    }

    @Test
    void testDeleteException() {
        when(this.wordRepository.findWordByWordId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordDto> response = this.wordService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals(response.getCode(), -2);

        verify(this.wordRepository, times(1)).findWordByWordId(any());

    }



}
