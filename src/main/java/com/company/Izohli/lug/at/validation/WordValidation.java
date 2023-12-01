package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.repository.AudioRepository;
import com.company.Izohli.lug.at.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WordValidation {
  private final CategoryRepository categoryRepository;
  private final AudioRepository audioRepository;

    public List<ErrorDto> wordValid(RequestWordDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.categoryRepository.findByCategoryId(dto.getCategoryId()).isEmpty()) {
            errorList.add(new ErrorDto("categoryId", String.format("categoryId with %d:id is not found!", dto.getCategoryId())));
        }
        /*if (this.audioRepository.findByAudioIdAndDeletedAtIsNull(dto.getAudioId()).isEmpty()) {
            errorList.add(new ErrorDto("audioId", String.format("audioId with %d:id is not found!", dto.getAudioId())));
        }*/
        return errorList;
    }
}
