package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.repository.NoteRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NoteValidation {
    private final NoteRepository noteRepository;

    public List<ErrorDto> noteValid(NoteDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.noteRepository.findByNoteIdAndDeletedAtIsNull(dto.getNoteId()).isEmpty()) {
            errorList.add(new ErrorDto("noteId", String.format("noteId with %d:id is not found!", dto.getNoteId())));
        }
        if (StringUtils.isBlank(dto.getTitle())) {
            errorList.add(new ErrorDto("title", "Title cannot be null or empty."));
        }
        return errorList;
    }
}
