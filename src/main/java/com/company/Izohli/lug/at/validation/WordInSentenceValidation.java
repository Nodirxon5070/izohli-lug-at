package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.repository.WordInSentenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WordInSentenceValidation {
    private final WordInSentenceRepository wordInSentenceRepository;

    public List<ErrorDto> wordInSentenceValid(WordInSentenceDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.wordInSentenceRepository.findByWordInSentenceIdAndDeletedAtIsNull(dto.getWordInSentenceId()).isEmpty()) {
            errorList.add(new ErrorDto("wordInSentenceId", String.format("wordInSentenceId with %d:id is not found!", dto.getWordInSentenceId())));
        }
        return errorList;
    }
}
