package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.dto.responseDto.DayWordDto;
import com.company.Izohli.lug.at.repository.DayWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DayWordValidation {
    private final DayWordRepository dayWordRepository;

    public List<ErrorDto> dayWordValid(DayWordDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.dayWordRepository.findByDayWordIdAndDeletedAtIsNull(dto.getDayWordId()).isEmpty()) {
            errorList.add(new ErrorDto("dayWordId", String.format("dayWordId with %d:id is not found!", dto.getDayWordId())));
        }
        return errorList;
    }
}
