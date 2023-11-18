package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.SentenceDto;
import com.company.Izohli.lug.at.repository.SentenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SentenceValidation {
    private final SentenceRepository sentenceRepository;

    public List<ErrorDto> sentenceValid(SentenceDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.sentenceRepository.findBySentenceIdAndDeletedAtIsNull(dto.getSentenceId()).isEmpty()) {
            errorList.add(new ErrorDto("sentenceId", String.format("sentenceId with %d:id is not found!", dto.getSentenceId())));
        }
        return errorList;
    }
}
