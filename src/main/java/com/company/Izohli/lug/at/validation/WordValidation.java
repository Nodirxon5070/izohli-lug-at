package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.responseDto.TypeDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.repository.WordRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WordValidation {
  private final WordRepository wordRepository;

    public List<ErrorDto> wordValid(WordDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.wordRepository.findByWordIdAndDeletedAtIsNull(dto.getWordId()).isEmpty()) {
            errorList.add(new ErrorDto("wordId", String.format("wordId with %d:id is not found!", dto.getWordId())));
        }
        return errorList;
    }
}
