package com.company.Izohli.lug.at.dto.requestDto;

import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.module.Word;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAudioDto {
    @NotBlank(message = "Path cannot be null or empty")
    private String path;
    private Set<WordDto> words;
}
