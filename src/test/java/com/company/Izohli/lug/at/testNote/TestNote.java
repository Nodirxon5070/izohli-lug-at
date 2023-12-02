package com.company.Izohli.lug.at.testNote;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestNoteDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.module.Note;
import com.company.Izohli.lug.at.repository.NoteRepository;
import com.company.Izohli.lug.at.service.NoteService;
import com.company.Izohli.lug.at.service.mapper.NoteMapper;
import com.company.Izohli.lug.at.validation.NoteValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestNote {


    private NoteService noteService;
    private NoteMapper noteMapper;
    private NoteValidation noteValidation;
    private NoteRepository noteRepository;


    @BeforeEach
    void initObject() {

        this.noteMapper = mock(NoteMapper.class);
        this.noteRepository = mock(NoteRepository.class);
        this.noteValidation = mock(NoteValidation.class);
        this.noteService = new NoteService(noteRepository, noteValidation, noteMapper);
    }


    @Test
    void testCreatePositive() {
        when(this.noteMapper.toEntity(any()))
                .thenReturn(Note.builder()
                        .noteId(1)
                        .wordId(1)
                        .build());
        when(this.noteMapper.toDto(any()))
                .thenReturn(NoteDto.builder()
                        .noteId(1)
                        .wordId(1)
                        .build());


        ResponseDto<NoteDto> responseDto = this.noteService.createEntity(any());

        assertEquals(responseDto.getCode(), 0, "Error responseDto code");
        assertEquals(responseDto.getData().getNoteId(), 1);
        assertTrue(responseDto.isSuccess());
        assertNotNull(responseDto.getData());
        assertNull(responseDto.getErrorList());

        verify(this.noteMapper, times(1)).toDto(any());
        verify(this.noteRepository, times(1)).save(any());

    }


    @Test
    void testCreateException() {
        when(noteMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<NoteDto> response = this.noteService.createEntity(any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());

    }

    @Test
    void testGetPositive() {
        when(this.noteMapper.toDtoWithWord(any()))
                .thenReturn(NoteDto.builder()
                        .noteId(1)
                        .wordId(1)
                        .build());
        when(this.noteRepository.findByNoteId(any()))
                .thenReturn(Optional.ofNullable(Note.builder()
                        .noteId(1)
                        .wordId(1)
                        .build()));

        ResponseDto<NoteDto> response = this.noteService.getEntity(any());

        assertEquals(response.getCode(), 0);
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(response.getData().getNoteId(), 1);

        verify(this.noteRepository, times(1)).findByNoteId(any());
        verify(this.noteMapper, times(1)).toDtoWithWord(any());
    }

    @Test
    void testGetNegative() {
        when(this.noteRepository.findByNoteId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<NoteDto> response = this.noteService.getEntity(any());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertFalse(response.isSuccess());


        verify(this.noteRepository, times(1)).findByNoteId(any());
    }

    @Test
    void testUpdatePositive() {
        when(noteRepository.findByNoteId(any()))
                .thenReturn(Optional.ofNullable(Note.builder()
                        .noteId(1)
                        .wordId(1)
                        .build()));

        when(noteMapper.updateNote(any(), any()))
                .thenReturn(Note.builder()
                        .noteId(2)
                        .wordId(1)
                        .build());

        when(noteMapper.toDto(any()))
                .thenReturn(NoteDto.builder()
                        .noteId(3)
                        .wordId(1)
                        .build());

        ResponseDto<NoteDto> response = this.noteService.updateEntity(any(), RequestNoteDto.builder()
                .wordId(1)
                .build());

        assertTrue(response.isSuccess());
        assertEquals(response.getCode(), 0);
        assertEquals(response.getData().getNoteId(), 3);

        verify(this.noteMapper, times(1)).toDto(any());
        verify(this.noteMapper, times(1)).updateNote(any(), any());
        verify(this.noteRepository, times(1)).save(any());
        verify(this.noteRepository, times(1)).findByNoteId(any());

    }

    @Test
    void testUpdateNegative() {
        when(this.noteRepository.findByNoteId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<NoteDto> response = this.noteService.updateEntity(1, any());

        assertFalse(response.isSuccess());
        assertEquals(response.getCode(), -1);
        assertNull(response.getData());
        assertEquals(response.getMessage(), "Note with 1 id is not found");

        verify(this.noteRepository, times(1)).findByNoteId(any());
    }

    @Test
    void testUpdateException() {
        when(this.noteRepository.findByNoteId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<NoteDto> response = this.noteService.updateEntity(1, any());

        assertEquals(response.getCode(), -2);
        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertNull(response.getErrorList());

        verify(this.noteRepository, times(1)).findByNoteId(any());
    }

    @Test
    void testDeletePositive() {
        when(this.noteRepository.findByNoteId(any()))
                .thenReturn(Optional.of(
                        Note.builder()
                                .noteId(1)
                                .wordId(1)
                                .build()));
        when(this.noteMapper.toDto(any()))
                .thenReturn(NoteDto.builder()
                        .noteId(3)
                        .wordId(1)
                        .build());
        ResponseDto<NoteDto> response = this.noteService.deleteEntity(any());

        assertTrue(response.isSuccess());
        assertEquals(response.getMessage(), "OK");
        assertNotNull(response.getData());
        assertEquals(response.getCode(), 0);

        verify(this.noteRepository, times(1)).findByNoteId(any());
        verify(this.noteRepository, times(1)).delete(any());
        verify(this.noteMapper, times(1)).toDto(any());

    }

    @Test
    void testDeleteNegative() {
        when(this.noteRepository.findByNoteId(any()))
                .thenReturn(Optional.empty());

        ResponseDto<NoteDto> response = this.noteService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertEquals(response.getMessage(), "Note with null id is not found");
        assertNull(response.getData());
        assertEquals(response.getCode(), -1);

        verify(this.noteRepository, times(1)).findByNoteId(any());
    }

    @Test
    void testDeleteException() {
        when(this.noteRepository.findByNoteId(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<NoteDto> response = this.noteService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals(response.getCode(), -2);

        verify(this.noteRepository, times(1)).findByNoteId(any());

    }


}
