package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestNoteDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.repository.NoteRepository;
import com.company.Izohli.lug.at.repository.WordRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NoteValidation {
    private final WordRepository wordRepository;

    public List<ErrorDto> noteValid(RequestNoteDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.wordRepository.findByWordIdAndDeletedAtIsNull(dto.getWordId()).isEmpty()) {
            errorList.add(new ErrorDto("wordId", String.format("wordId with %d:id is not found!", dto.getWordId())));
        }
        return errorList;
    }
}
