package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.responseDto.SentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.TypeDto;
import com.company.Izohli.lug.at.repository.TypeRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TypeValidation {
    private final TypeRepository typeRepository;

    public List<ErrorDto> typeValid(TypeDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.typeRepository.findByTypeIdAndDeletedAtIsNull(dto.getTypeId()).isEmpty()) {
            errorList.add(new ErrorDto("typeId", String.format("typeId with %d:id is not found!", dto.getTypeId())));
        }
        if (StringUtils.isBlank(dto.getName())) {
            errorList.add(new ErrorDto("name", "Name cannot be null or empty."));
        }
        return errorList;
    }
}
