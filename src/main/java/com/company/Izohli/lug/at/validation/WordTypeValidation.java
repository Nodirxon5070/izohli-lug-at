package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordTypeDto;
import com.company.Izohli.lug.at.repository.TypeRepository;
import com.company.Izohli.lug.at.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WordTypeValidation {
    private final TypeRepository typeRepository;
    private final WordRepository wordRepository;

    public List<ErrorDto> wordTypeValid(RequestWordTypeDto dto){
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.typeRepository.findByTypeId(dto.getTypeId()).isEmpty()){
            errorList.add(new ErrorDto("type",String.format("%d id is not found!",dto.getTypeId())));
        }
        if (this.wordRepository.findWordByWordId(dto.getWordId()).isEmpty()){
            errorList.add(new ErrorDto("word",String.format("%d id is not found!",dto.getWordId())));
        }
        return errorList;
    }
}
