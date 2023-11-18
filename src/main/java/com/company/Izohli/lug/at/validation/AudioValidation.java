package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.repository.AudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AudioValidation {
    private final AudioRepository audioRepository;
   /* public List<ErrorDto> audioValid(RequestAudioDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.audioRepository.findByAudioIdAndDeletedAtIsNull(dto.getAudioId()).isEmpty()) {
            errorList.add(new ErrorDto("audioId", String.format("audioId with %d:id is not found!", dto.getAudioId())));
        }
        return errorList;
    }*/
}
