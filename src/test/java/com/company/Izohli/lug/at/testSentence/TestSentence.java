package com.company.Izohli.lug.at.testSentence;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestNoteDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.dto.responseDto.SentenceDto;
import com.company.Izohli.lug.at.module.Note;
import com.company.Izohli.lug.at.module.Sentence;
import com.company.Izohli.lug.at.repository.SentenceRepository;
import com.company.Izohli.lug.at.service.SentenceService;
import com.company.Izohli.lug.at.service.mapper.SentenceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestSentence {

    private SentenceService sentenceService;
    private SentenceMapper sentenceMapper;
    private SentenceRepository sentenceRepository;


    @BeforeEach
    void initObject() {

        this.sentenceMapper = mock(SentenceMapper.class);
        this.sentenceRepository = mock(SentenceRepository.class);
        this.sentenceService = new SentenceService(sentenceRepository, sentenceMapper);
    }


    @Test
    void testCreatePositive() {
        when(this.sentenceMapper.toEntity(any()))
                .thenReturn(Sentence.builder()
                        .sentenceId(1)
                        .content("content")

                        .build());
        when(this.sentenceMapper.toDto(any()))
                .thenReturn(SentenceDto.builder()
                        .sentenceId(1)
                        .content("content")
                        .build());


        ResponseDto<SentenceDto> responseDto = this.sentenceService.createEntity(any());

        assertEquals(responseDto.getCode(), 0, "Error responseDto code");
        assertEquals(responseDto.getData().getSentenceId(), 1);
        assertTrue(responseDto.isSuccess());
        assertNotNull(responseDto.getData());
        assertNull(responseDto.getErrorList());

        verify(this.sentenceMapper, times(1)).toDto(any());
        verify(this.sentenceRepository, times(1)).save(any());

    }


    @Test
    void testCreateException() {
        when(sentenceMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<SentenceDto> response = this.sentenceService.createEntity(any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());

    }

    @Test
    void testGetPositive() {
        when(this.sentenceMapper.toDtoWithWordInSentence(any()))
                .thenReturn(SentenceDto.builder()
                        .sentenceId(1)
                        .content("content")
                        .build());
        when(this.sentenceRepository.findBySentenceId(any()))
                .thenReturn(Optional.ofNullable(Sentence.builder()
                        .sentenceId(1)
                        .content("content")
                        .build()));

        ResponseDto<SentenceDto> response = this.sentenceService.getEntity(any());

        assertEquals(response.getCode(), 0);
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(response.getData().getSentenceId(), 1);

        verify(this.sentenceRepository, times(1)).findBySentenceId(any());
        verify(this.sentenceMapper, times(1)).toDtoWithWordInSentence(any());
    }

    @Test
    void testGetNegative() {
        when(this.sentenceRepository.findBySentenceId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SentenceDto> response = this.sentenceService.getEntity(any());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertFalse(response.isSuccess());


        verify(this.sentenceRepository, times(1)).findBySentenceId(any());
    }
    @Test
    void testUpdatePositive() {
        when(sentenceRepository.findBySentenceId(any()))
                .thenReturn(Optional.ofNullable(Sentence.builder()
                        .sentenceId(1)
                        .content("content")
                        .build()));

        when(sentenceMapper.updateSentence(any(), any()))
                .thenReturn(Sentence.builder()
                        .sentenceId(2)
                        .content("content")
                        .build());

        when(sentenceMapper.toDto(any()))
                .thenReturn(SentenceDto.builder()
                        .sentenceId(3)
                        .content("content")
                        .build());

        ResponseDto<SentenceDto> response = this.sentenceService.updateEntity(any(),
                RequestSentenceDto.builder()
                .content("content")
                .build());

        assertTrue(response.isSuccess());
        assertEquals(response.getCode(), 0);
        assertEquals(response.getData().getSentenceId(), 3);

        verify(this.sentenceMapper, times(1)).toDto(any());
        verify(this.sentenceMapper, times(1)).updateSentence(any(), any());
        verify(this.sentenceRepository, times(1)).save(any());
        verify(this.sentenceRepository, times(1)).findBySentenceId(any());

    }

    @Test
    void testUpdateNegative() {
        when(this.sentenceRepository.findBySentenceId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SentenceDto> response = this.sentenceService.updateEntity(1, any());

        assertFalse(response.isSuccess());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertEquals(response.getMessage(), "Sentence with 1 id is not found");

        verify(this.sentenceRepository, times(1)).findBySentenceId(any());
    }

    @Test
    void testUpdateException() {
        when(this.sentenceRepository.findBySentenceId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<SentenceDto> response = this.sentenceService.updateEntity(1, any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertNull(response.getErrorList());

        verify(this.sentenceRepository, times(1)).findBySentenceId(any());
    }

    @Test
    void testDeletePositive() {
        when(this.sentenceRepository.findBySentenceId(any()))
                .thenReturn(Optional.of(
                        Sentence.builder()
                                .sentenceId(1)
                                .content("content")
                                .build()));
        when(this.sentenceMapper.toDto(any()))
                .thenReturn(SentenceDto.builder()
                        .sentenceId(3)
                        .content("content")
                        .build());
        ResponseDto<SentenceDto> response = this.sentenceService.deleteEntity(any());

        assertTrue(response.isSuccess());
        assertEquals(response.getMessage(), "OK");
        assertNotNull(response.getData());
        assertEquals(response.getCode(), 0);

        verify(this.sentenceRepository, times(1)).findBySentenceId(any());
        verify(this.sentenceRepository, times(1)).delete(any());
        verify(this.sentenceMapper, times(1)).toDto(any());

    }

    @Test
    void testDeleteNegative() {
        when(this.sentenceRepository.findBySentenceId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SentenceDto> response = this.sentenceService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertEquals(response.getMessage(), "Sentence with null id is not found");
        assertNull(response.getData());
        assertEquals(response.getCode(), -1);

        verify(this.sentenceRepository, times(1)).findBySentenceId(any());
    }

    @Test
    void testDeleteException() {
        when(this.sentenceRepository.findBySentenceId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<SentenceDto> response = this.sentenceService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals(response.getCode(), -2);

        verify(this.sentenceRepository, times(1)).findBySentenceId(any());

    }


}
