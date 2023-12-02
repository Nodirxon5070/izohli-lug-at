package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordInSentenceDto;
import com.company.Izohli.lug.at.repository.SentenceRepository;
import com.company.Izohli.lug.at.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WordInSentenceValidation {
    private final WordRepository wordRepository;
    private final SentenceRepository sentenceRepository;

    public List<ErrorDto> wordInSentenceValid(RequestWordInSentenceDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.wordRepository.findWordByWordId(dto.getWordId()).isEmpty()) {
            errorList.add(new ErrorDto("wordId", String.format("wordId with %d:id is not found!", dto.getWordId())));
        }
        if (this.sentenceRepository.findBySentenceId(dto.getSentenceId()).isEmpty()) {
            errorList.add(new ErrorDto("sentenceId", String.format("sentenceId with %d:id is not found!", dto.getSentenceId())));
        }
        return errorList;
    }
}
