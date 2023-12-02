package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestNoteDto;
import com.company.Izohli.lug.at.repository.WordRepository;
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
        if (this.wordRepository.findWordByWordId(dto.getWordId()).isEmpty()) {
            errorList.add(new ErrorDto("wordId", String.format("wordId with %d:id is not found!", dto.getWordId())));
        }
        return errorList;
    }
}
