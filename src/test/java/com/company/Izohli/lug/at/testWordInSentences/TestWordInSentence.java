package com.company.Izohli.lug.at.testWordInSentences;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordInSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.module.WordInSentence;
import com.company.Izohli.lug.at.repository.WordInSentenceRepository;
import com.company.Izohli.lug.at.service.WordInSentenceService;
import com.company.Izohli.lug.at.service.mapper.WordInSentenceMapper;
import com.company.Izohli.lug.at.validation.WordInSentenceValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestWordInSentence {
    private WordInSentenceService wordInSentenceService;
    private WordInSentenceMapper wordInSentenceMapper;
    private WordInSentenceValidation wordInSentenceValidation;
    private WordInSentenceRepository wordInSentenceRepository;


    @BeforeEach
    void initObject() {

        this.wordInSentenceMapper = mock(WordInSentenceMapper.class);
        this.wordInSentenceValidation = mock(WordInSentenceValidation.class);
        this.wordInSentenceRepository = mock(WordInSentenceRepository.class);
        this.wordInSentenceService = new WordInSentenceService(wordInSentenceRepository, wordInSentenceMapper,wordInSentenceValidation);
    }



    @Test
    void testCreatePositive() {
        when(this.wordInSentenceMapper.toEntity(any()))
                .thenReturn(WordInSentence.builder()
                        .wordInSentenceId(1)
                        .wordId(1)
                        .build());
        when(this.wordInSentenceMapper.toDto(any()))
                .thenReturn(WordInSentenceDto.builder()
                        .wordInSentenceId(1)
                        .wordId(1)
                        .build());


        ResponseDto<WordInSentenceDto> responseDto = this.wordInSentenceService.createEntity(any());

        assertEquals(responseDto.getCode(), 0, "Error responseDto code");
        assertEquals(responseDto.getData().getWordInSentenceId(), 1);
        assertTrue(responseDto.isSuccess());
        assertNotNull(responseDto.getData());
        assertNull(responseDto.getErrorList());

        verify(this.wordInSentenceMapper, times(1)).toDto(any());
        verify(this.wordInSentenceRepository, times(1)).save(any());

    }


    @Test
    void testCreateException() {
        when(wordInSentenceMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordInSentenceDto> response = this.wordInSentenceService.createEntity(any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());

    }

    @Test
    void testGetPositive() {
        when(this.wordInSentenceMapper.toDtoWithWordsAndSentence(any()))
                .thenReturn(WordInSentenceDto.builder()
                        .wordInSentenceId(1)
                        .wordId(1)
                        .build());
        when(this.wordInSentenceRepository.findByWordInSentenceId(any()))
                .thenReturn(Optional.ofNullable(WordInSentence.builder()
                        .wordInSentenceId(1)
                        .wordId(1)
                        .build()));

        ResponseDto<WordInSentenceDto> response = this.wordInSentenceService.getEntity(any());

        assertEquals(response.getCode(), 0);
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(response.getData().getWordInSentenceId(), 1);

        verify(this.wordInSentenceRepository, times(1)).findByWordInSentenceId(any());
        verify(this.wordInSentenceMapper, times(1)).toDtoWithWordsAndSentence(any());
    }

    @Test
    void testGetNegative() {
        when(this.wordInSentenceRepository.findByWordInSentenceId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordInSentenceDto> response = this.wordInSentenceService.getEntity(any());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertFalse(response.isSuccess());


        verify(this.wordInSentenceRepository, times(1)).findByWordInSentenceId(any());
    }
    @Test
    void testUpdatePositive() {
        when(wordInSentenceRepository.findByWordInSentenceId(any()))
                .thenReturn(Optional.ofNullable(WordInSentence.builder()
                        .wordInSentenceId(1)
                        .wordId(1)
                        .build()));

        when(wordInSentenceMapper.updateWordInSentence(any(), any()))
                .thenReturn(WordInSentence.builder()
                        .wordInSentenceId(2)
                        .wordId(1)
                        .build());

        when(wordInSentenceMapper.toDto(any()))
                .thenReturn(WordInSentenceDto.builder()
                        .wordInSentenceId(3)
                        .wordId(1)
                        .build());

        ResponseDto<WordInSentenceDto> response = this.wordInSentenceService.updateEntity(any(),
                RequestWordInSentenceDto.builder()
                        .wordId(1)
                        .build());

        assertTrue(response.isSuccess());
        assertEquals(response.getCode(), 0);
        assertEquals(response.getData().getWordInSentenceId(), 3);

        verify(this.wordInSentenceMapper, times(1)).toDto(any());
        verify(this.wordInSentenceMapper, times(1)).updateWordInSentence(any(), any());
        verify(this.wordInSentenceRepository, times(1)).save(any());
        verify(this.wordInSentenceRepository, times(1)).findByWordInSentenceId(any());

    }

    @Test
    void testUpdateNegative() {
        when(this.wordInSentenceRepository.findByWordInSentenceId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordInSentenceDto> response = this.wordInSentenceService.updateEntity(1, any());

        assertFalse(response.isSuccess());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertEquals(response.getMessage(), "WordInSentence with 1 id is not found");

        verify(this.wordInSentenceRepository, times(1)).findByWordInSentenceId(any());
    }

    @Test
    void testUpdateException() {
        when(this.wordInSentenceRepository.findByWordInSentenceId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordInSentenceDto> response = this.wordInSentenceService.updateEntity(1, any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertNull(response.getErrorList());

        verify(this.wordInSentenceRepository, times(1)).findByWordInSentenceId(any());
    }

    @Test
    void testDeletePositive() {
        when(this.wordInSentenceRepository.findByWordInSentenceId(any()))
                .thenReturn(Optional.of(
                        WordInSentence.builder()
                                .wordInSentenceId(1)
                                .wordId(1)
                                .build()));
        when(this.wordInSentenceMapper.toDto(any()))
                .thenReturn(WordInSentenceDto.builder()
                        .wordInSentenceId(3)
                        .wordId(1)
                        .build());
        ResponseDto<WordInSentenceDto> response = this.wordInSentenceService.deleteEntity(any());

        assertTrue(response.isSuccess());
        assertEquals(response.getMessage(), "Ok");
        assertNotNull(response.getData());
        assertEquals(response.getCode(), 0);

        verify(this.wordInSentenceRepository, times(1)).findByWordInSentenceId(any());
        verify(this.wordInSentenceRepository, times(1)).delete(any());
        verify(this.wordInSentenceMapper, times(1)).toDto(any());

    }

    @Test
    void testDeleteNegative() {
        when(this.wordInSentenceRepository.findByWordInSentenceId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordInSentenceDto> response = this.wordInSentenceService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertEquals(response.getMessage(), "WordInSentence with null id is not found");
        assertNull(response.getData());
        assertEquals(response.getCode(), -1);

        verify(this.wordInSentenceRepository, times(1)).findByWordInSentenceId(any());
    }

    @Test
    void testDeleteException() {
        when(this.wordInSentenceRepository.findByWordInSentenceId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordInSentenceDto> response = this.wordInSentenceService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals(response.getCode(), -2);

        verify(this.wordInSentenceRepository, times(1)).findByWordInSentenceId(any());

    }



}
