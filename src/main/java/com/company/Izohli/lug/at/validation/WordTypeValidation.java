package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.WordTypeDto;
import com.company.Izohli.lug.at.repository.WordTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WordTypeValidation {
    private final WordTypeRepository wordTypeRepository;

    public List<ErrorDto> wordTypeSentenceValid(WordTypeDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.wordTypeRepository.findByWordTypeIdAndDeletedAtIsNull(dto.getWordTypeId()).isEmpty()) {
            errorList.add(new ErrorDto("wordTypeId", String.format("wordTypeId with %d:id is not found!", dto.getWordTypeId())));
        }
        return errorList;
    }
}
